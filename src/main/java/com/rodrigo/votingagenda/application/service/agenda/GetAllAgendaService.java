package com.rodrigo.votingagenda.application.service.agenda;

import com.rodrigo.votingagenda.application.repository.AgendaRepository;
import com.rodrigo.votingagenda.contract.agenda.response.AgendaResponse;
import com.rodrigo.votingagenda.contract.agenda.response.GetAllAgendaResponse;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class GetAllAgendaService {

    private final AgendaRepository agendaRepository;

    public ResponseEntity<GetAllAgendaResponse> getAllAgenda(){
        List<AgendaResponse> response = agendaRepository.findAll()
                .stream()
                .map(agenda -> new AgendaResponse(
                        agenda.getId(),
                        agenda.getTitle(),
                        agenda.getDescription(),
                        agenda.getCreatedAt(),
                        agenda.getUpdatedAt()
                )).toList();
        return ResponseEntity.ok(new GetAllAgendaResponse(response));

    }
}
