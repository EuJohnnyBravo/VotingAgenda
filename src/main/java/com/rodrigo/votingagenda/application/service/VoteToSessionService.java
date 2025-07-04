package com.rodrigo.votingagenda.application.service;

import com.rodrigo.votingagenda.application.model.Agenda;
import com.rodrigo.votingagenda.application.model.Session;
import com.rodrigo.votingagenda.application.model.Vote;
import com.rodrigo.votingagenda.application.repository.AgendaRepository;
import com.rodrigo.votingagenda.application.repository.SessionRepository;
import com.rodrigo.votingagenda.application.repository.VoteRepository;
import com.rodrigo.votingagenda.common.enums.Votes;
import com.rodrigo.votingagenda.common.exception.custom.InvalidVoteException;
import com.rodrigo.votingagenda.common.exception.custom.NotFoundException;
import com.rodrigo.votingagenda.contract.agenda.request.VoteRequest;
import com.rodrigo.votingagenda.contract.agenda.response.VoteResponse;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Builder
public class VoteToSessionService {
    private final AgendaRepository agendaRepository;
    private final SessionRepository sessionRepository;
    private final VoteRepository voteRepository;

    public ResponseEntity<VoteResponse> voteToSession(VoteRequest payload, String agendaId , String sessionId){
        Agenda agenda = agendaRepository.findById(UUID.fromString(agendaId))
                .orElseThrow(() -> new NotFoundException("Pauta não encontrada com o ID: " + agendaId));
        Session session = sessionRepository.findById(UUID.fromString(sessionId))
                .orElseThrow(() -> new NotFoundException("Sessão não encontrada com o ID: " + sessionId));

        if (session.isClosed()) {
            throw new InvalidVoteException("Sessão já fechada");
        }

        Vote vote = new Vote();
        vote.setId(UUID.randomUUID());
        vote.setAgendaId(agenda.getId());
        vote.setSessionId(session.getId());
        vote.setCpf(payload.cpf());
        vote.setVotedAt(Instant.now());
        vote.setVote(Votes.fromString(payload.vote()));


        session.setVotes(vote);
        Vote savedVote = voteRepository.save(vote);
        Session savedSession = sessionRepository.save(session);
        agenda.getSessions().stream()
                .filter(s -> s.getId().equals(savedSession.getId()))
                .findFirst()
                .ifPresent(s -> s.getVotes().add(savedVote));

        agendaRepository.save(agenda);

        VoteResponse response = new VoteResponse(savedVote.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
