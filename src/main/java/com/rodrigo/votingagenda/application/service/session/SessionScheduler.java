package com.rodrigo.votingagenda.application.service.session;

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
    private final SessionService sessionService;

    @Scheduled(fixedRate = 5000)
    public void schedule() {
        List<Session> openSessions = sessionRepository.findByClosedFalse();
        openSessions.forEach(session -> {
            Instant now = Instant.now();
            Instant endOfSession = session.getCreatedAt().plusSeconds(session.getDurationInMinutes() + 60L);

            if (now.isAfter(endOfSession)) {
                session.closeSession();
                sessionRepository.save(session);
                sessionService.closeAndNotify(session.getId());
            }
        });
    }
}
