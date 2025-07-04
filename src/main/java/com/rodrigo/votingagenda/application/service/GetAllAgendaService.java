package com.rodrigo.votingagenda.application.service;

import com.rodrigo.votingagenda.application.repository.AgendaRepository;
import com.rodrigo.votingagenda.contract.agenda.response.AgendaResponse;
import com.rodrigo.votingagenda.contract.agenda.response.GetAllAgendaResponse;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Builder
public class GetAllAgendaService {

    private final AgendaRepository agendaRepository;

    public ResponseEntity<GetAllAgendaResponse> getAllAgenda(){
        List<AgendaResponse> response = agendaRepository.findAll()
                .stream()
                .map(agenda -> {
                    return new AgendaResponse(
                            agenda.getId(),
                            agenda.getTitle(),
                            agenda.getDescription(),
                            agenda.getCreatedAt()
                    );
                }).toList();
        return ResponseEntity.ok(new GetAllAgendaResponse(response));

    }
}
