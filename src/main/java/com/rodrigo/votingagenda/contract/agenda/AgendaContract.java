package com.rodrigo.votingagenda.contract.agenda;

import com.rodrigo.votingagenda.application.service.CreateAgendaService;
import com.rodrigo.votingagenda.contract.agenda.request.AgendaRequest;
import com.rodrigo.votingagenda.contract.agenda.response.AgendaReponse;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agendas")
@Builder
public class AgendaContract {

    private final CreateAgendaService createAgendaService;

    @PostMapping
    public ResponseEntity<AgendaReponse> createAgenda(@Valid @RequestBody AgendaRequest payload) {
        return createAgendaService.createAgenda(payload);
    }



}
