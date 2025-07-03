package com.rodrigo.votingagenda.contract.agenda.request;

import jakarta.validation.constraints.NotEmpty;

public record AgendaRequest(
        @NotEmpty(message = "Precisa de um titúlo")
        String title,
        String description
) {
}
