package com.rodrigo.votingagenda.contract.session;

import com.rodrigo.votingagenda.application.service.session.AddSessionInAgendaService;
import com.rodrigo.votingagenda.contract.session.response.SessionResponse;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/agenda/{agendaId}/sessions")
@Builder
public class SessionContract {

    private final AddSessionInAgendaService addSessionInAgenda;

    @PostMapping()
    public ResponseEntity<SessionResponse> createSessionInAgenda(
            @PathVariable String agendaId,
            @RequestParam(defaultValue = "1") int duration) {
        return addSessionInAgenda.addSession(agendaId, duration);
    }
}
