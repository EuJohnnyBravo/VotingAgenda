package com.rodrigo.votingagenda.contract.agenda;

import com.rodrigo.votingagenda.application.service.AddSessionToAgendaService;
import com.rodrigo.votingagenda.application.service.CreateAgendaService;
import com.rodrigo.votingagenda.application.service.VoteToSessionService;
import com.rodrigo.votingagenda.contract.agenda.request.AgendaRequest;
import com.rodrigo.votingagenda.contract.agenda.request.VoteRequest;
import com.rodrigo.votingagenda.contract.agenda.response.AgendaReponse;
import com.rodrigo.votingagenda.contract.agenda.response.SessionResponse;
import com.rodrigo.votingagenda.contract.agenda.response.VoteResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/agendas")
@Builder
public class AgendaContract {

    private final CreateAgendaService createAgenda;
    private final AddSessionToAgendaService addSessionToAgendaService;
    private final VoteToSessionService voteToSession;

    @PostMapping
    public ResponseEntity<AgendaReponse> createAgenda(@Valid @RequestBody AgendaRequest payload) {
        return createAgenda.createAgenda(payload);
    }

    @PostMapping("/{id}")
    public ResponseEntity<SessionResponse> createSessionInAgenda(
            @Valid @PathVariable @NotNull String id,
            @RequestParam(defaultValue = "1") int duration) {
        return addSessionToAgendaService.addSessionToAgenda(id, duration);
    }

    @PostMapping("/{id}/{sessionId}/vote")
    public ResponseEntity<VoteResponse> voteToSession(
            @Valid @PathVariable @NotNull String id,
            @Valid @PathVariable @NotNull String sessionId,
            @Valid @RequestBody VoteRequest payload) {
        return voteToSession.voteToSession(payload, id, sessionId);
    }



}
