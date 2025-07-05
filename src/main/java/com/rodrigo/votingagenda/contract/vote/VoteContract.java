package com.rodrigo.votingagenda.contract.vote;

import com.rodrigo.votingagenda.application.service.vote.VoteSessionService;
import com.rodrigo.votingagenda.contract.vote.request.VoteRequest;
import com.rodrigo.votingagenda.contract.vote.response.VoteResponse;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessions/{sessionId}/votes")
@Builder
public class VoteContract {

    private final VoteSessionService voteSession;

    @PostMapping()
    public ResponseEntity<VoteResponse> vote(
            @PathVariable String sessionId,
            @Valid @RequestBody VoteRequest payload) {
        return voteSession.voteSession(payload, sessionId);
    }
}
