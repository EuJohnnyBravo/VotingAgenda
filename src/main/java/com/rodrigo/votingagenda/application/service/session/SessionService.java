package com.rodrigo.votingagenda.application.service.session;

import com.rodrigo.votingagenda.application.model.Session;
import com.rodrigo.votingagenda.application.repository.SessionRepository;
import com.rodrigo.votingagenda.application.service.kafka.KafkaProducerService;
import com.rodrigo.votingagenda.common.exception.custom.NotFoundException;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Builder
public class SessionService {

    private final SessionRepository sessionRepository;
    private final KafkaProducerService kafkaProducerService;
    private static final String TOPIC_NAME = "session-closed";

    public void closeAndNotify(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new NotFoundException("Sessão não encontrada"));

        session.closeSession();
        sessionRepository.save(session);

        // Envio de mensagem após fechar a sessão
        String message = "Sessão " + sessionId + " foi fechada.";
        kafkaProducerService.sendMessage(TOPIC_NAME, message);
    }


}
