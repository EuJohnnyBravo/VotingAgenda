package com.rodrigo.votingagenda.contract.session;


import com.rodrigo.votingagenda.application.service.AddSessionToAgendaService;
import com.rodrigo.votingagenda.contract.session.request.SessionResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessions")
@Builder
public class SessionContract {

    private final AddSessionToAgendaService createSessionToAgendaService;

    @PostMapping("/{id}")
    public ResponseEntity<SessionResponse> createSessionInAgenda(
            @Valid @PathVariable @NotNull String id,
            @RequestParam(defaultValue = "1") int duration) {
        return createSessionToAgendaService.addSessionToAgenda(id, duration);
    }
}
