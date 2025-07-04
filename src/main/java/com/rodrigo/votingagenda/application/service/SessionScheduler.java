package com.rodrigo.votingagenda.application.service;

import com.rodrigo.votingagenda.application.model.Session;
import com.rodrigo.votingagenda.application.repository.SessionRepository;
import lombok.Builder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Builder
public class SessionScheduler {

    private final SessionRepository sessionRepository;

    @Scheduled(fixedRate = 5000)
    public void schedule() {
        List<Session> openSessions = sessionRepository.findByIsClosedFalse();
        openSessions.forEach(session -> {
            Instant now = Instant.now();
            Instant endOfSession = session.getCreatedAt().plusSeconds(session.getDurationInMinutes() + 60L);

            if (now.isAfter(endOfSession)) {
                session.closeSession();
                sessionRepository.save(session);
                System.out.println("Sessão fechada em: " + now);
            }
        });
    }
}
