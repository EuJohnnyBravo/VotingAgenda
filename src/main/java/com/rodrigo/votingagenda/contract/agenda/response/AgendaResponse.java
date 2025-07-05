package com.rodrigo.votingagenda.contract.agenda.response;

import java.time.Instant;
import java.util.UUID;

public record AgendaResponse(
        UUID id,
        String title,
        String description,
        Instant createdAt,
        Instant updatedAt
) {
}
