package com.rodrigo.votingagenda.contract.vote.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record VoteRequest(
        @NotEmpty(message = "CPF não pode estar vazio")
        @Pattern(regexp = "^\\d{11}$", message = "CPF deve estar no formato 00011100022")
        String cpf,
        @NotNull(message = "voto não pode ser nulo")
        boolean vote
) {
}
