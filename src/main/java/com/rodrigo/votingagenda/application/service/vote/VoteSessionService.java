package com.rodrigo.votingagenda.application.service.vote;

import com.rodrigo.votingagenda.application.model.Session;
import com.rodrigo.votingagenda.application.model.Vote;
import com.rodrigo.votingagenda.application.repository.SessionRepository;
import com.rodrigo.votingagenda.application.repository.VoteRepository;
import com.rodrigo.votingagenda.common.exception.custom.InvalidVoteException;
import com.rodrigo.votingagenda.common.exception.custom.NotFoundException;
import com.rodrigo.votingagenda.contract.vote.request.VoteRequest;
import com.rodrigo.votingagenda.contract.vote.response.VoteResponse;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Builder
public class VoteSessionService {

    private final SessionRepository sessionRepository;
    private final VoteRepository voteRepository;
    private final ValidateCpfService validateCpfService;

    public ResponseEntity<VoteResponse> voteSession(VoteRequest payload, String sessionId){
        Session session = sessionRepository.findById(UUID.fromString(sessionId))
                .orElseThrow(() -> new NotFoundException("Sessão não encontrada com o ID: " + sessionId));

        if (session.isClosed()) {
            throw new InvalidVoteException("Sessão já fechada");
        }

        if(voteRepository.existsBySessionIdAndCpf(session.getId(), payload.cpf())){
            throw new InvalidVoteException("CPF já votou nesta sessão: " + payload.cpf() + " : " + sessionId);
        }

//        if(!validateCpfService.canVote(payload.cpf())){
//            throw new InvalidVoteException("CPF invalido para votar: " + payload.cpf());
//        }

        Vote vote = new Vote();
        vote.setId(UUID.randomUUID());
        vote.setAgendaId(session.getAgendaId());
        vote.setSessionId(session.getId());
        vote.setCpf(payload.cpf());
        vote.setVotedAt(Instant.now());
        vote.setVote(payload.vote());

        Vote savedVote = voteRepository.save(vote);

        VoteResponse response = new VoteResponse(savedVote.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
