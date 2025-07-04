package com.rodrigo.votingagenda.contract.agenda.response;

import java.util.List;

public record GetAllAgendaResponse(
        List<AgendaResponse> agendas
) {
}
