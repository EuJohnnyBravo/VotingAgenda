package com.rodrigo.votingagenda.application.service;

import com.rodrigo.votingagenda.application.model.Agenda;
import com.rodrigo.votingagenda.application.model.Session;
import com.rodrigo.votingagenda.application.repository.AgendaRepository;
import com.rodrigo.votingagenda.application.repository.SessionRepository;
import com.rodrigo.votingagenda.common.exception.custom.NotFoundException;
import com.rodrigo.votingagenda.contract.session.request.SessionResponse;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.UUID;

@Service
@Builder
public class AddSessionToAgendaService {
    private final SessionRepository sessionRepository;
    private final AgendaRepository agendaRepository;

    public ResponseEntity<SessionResponse> addSessionToAgenda(String id, int duration) {
        Session session = new Session();
        Agenda agenda = agendaRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Agenda não encontrada com o ID: " + id)
                );

        session.setId(UUID.randomUUID());
        session.setAgendaId(UUID.fromString(id));
        session.setCreatedAt(Instant.now());
        session.setClosedAt(Instant.now().plus(Duration.ofMinutes(duration)));
        session.setVotes(new HashSet<>());
        session.setDurationInMinutes(duration);

        Session savedSession = sessionRepository.save(session);
        agenda.getSessions().add(savedSession);
        agenda.setUpdatedAt(Instant.now());
        agendaRepository.save(agenda);

        SessionResponse response = new SessionResponse(savedSession.getId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
