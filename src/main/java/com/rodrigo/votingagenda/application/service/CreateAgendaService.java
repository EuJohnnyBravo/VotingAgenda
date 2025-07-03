package com.rodrigo.votingagenda.application.service;

import com.rodrigo.votingagenda.application.model.Agenda;
import com.rodrigo.votingagenda.application.repository.AgendaRepository;
import com.rodrigo.votingagenda.contract.agenda.request.CreateAgendaRequest;
import com.rodrigo.votingagenda.contract.agenda.response.CreateAgendaReponse;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

@Service
@Builder
public class CreateAgendaService {
    private final AgendaRepository agendaRepository;

    public ResponseEntity<CreateAgendaReponse> createAgenda(CreateAgendaRequest payload) {
        Agenda agenda = new Agenda();
        agenda.setId(UUID.randomUUID());
        agenda.setTitle(payload.title());
        agenda.setDescription(payload.description());
        agenda.setSessions(new ArrayList<>());
        agenda.setCreatedAt(Instant.now());
        agenda.setUpdatedAt(Instant.now());

        Agenda savedAgenda = agendaRepository.save(agenda);
        CreateAgendaReponse response = new CreateAgendaReponse(savedAgenda.getId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
