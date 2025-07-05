package com.rodrigo.votingagenda.contract.agenda;

import com.rodrigo.votingagenda.application.service.agenda.CreateAgendaService;
import com.rodrigo.votingagenda.application.service.agenda.GetAllAgendaService;
import com.rodrigo.votingagenda.contract.agenda.request.AgendaRequest;
import com.rodrigo.votingagenda.contract.agenda.response.AgendaShortReponse;
import com.rodrigo.votingagenda.contract.agenda.response.GetAllAgendaResponse;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/agenda")
@Builder
public class AgendaContract {

    private final CreateAgendaService createAgenda;
    private final GetAllAgendaService getAllAgenda;

    @PostMapping
    public ResponseEntity<AgendaShortReponse> createAgenda(@Valid @RequestBody AgendaRequest payload) {
        return createAgenda.createAgenda(payload);
    }

    @GetMapping()
    public ResponseEntity<GetAllAgendaResponse> getAllAgendas() {
        return getAllAgenda.getAllAgenda();
    }

}
