package com.rodrigo.votingagenda.application.service.session;

import com.rodrigo.votingagenda.application.model.Agenda;
import com.rodrigo.votingagenda.application.model.Session;
import com.rodrigo.votingagenda.application.repository.AgendaRepository;
import com.rodrigo.votingagenda.application.repository.SessionRepository;
import com.rodrigo.votingagenda.application.repository.VoteRepository;
import com.rodrigo.votingagenda.application.service.kafka.KafkaProducerService;
import com.rodrigo.votingagenda.application.service.vote.VoteSessionService;
import com.rodrigo.votingagenda.common.exception.custom.NotFoundException;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Builder
public class SessionService {

    private final AgendaRepository agendaRepository;
    private final SessionRepository sessionRepository;
    private final VoteRepository voteRepository;
    private final VoteSessionService voteSessionService;
    private final KafkaProducerService kafkaProducerService;
    private static final String TOPIC_NAME = "session-closed";


    public void closeAndNotify(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new NotFoundException("Sessão não encontrada"));

        Agenda agenda = agendaRepository.findById(session.getAgendaId())
                .orElseThrow(() -> new NotFoundException("Agenda não encontrada"));

        int totalVotes = voteRepository.countBySessionId(session.getId());
        int approvedVotes = voteRepository.countBySessionIdAndVoteTrue(session.getId());
        int deniedVotes = totalVotes - approvedVotes;

        session.closeSession();
        sessionRepository.save(session);

        String messageResult;
        if(approvedVotes > deniedVotes){
            messageResult = "Resultado: Aprovado com " + approvedVotes + " votos.";
        }
        else if(approvedVotes == deniedVotes){
            messageResult = "Resultado: Empate com " + approvedVotes + " votos.";
        }
        else{
            messageResult = "Resultado: Reprovado com " + deniedVotes + " votos.";
        }

        // Envio de mensagem após fechar a sessão
        String message = "Agenda: " + agenda.getTitle() + " | Sessão "
                + sessionId + " foi fechada. Total de Votos: " + totalVotes + ". "
                + messageResult;
        kafkaProducerService.sendMessage(TOPIC_NAME, message);
    }


}
