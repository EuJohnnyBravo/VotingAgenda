package com.rodrigo.votingagenda.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    private UUID id;
    private UUID agendaId;
    private Instant createdAt;
    private Instant closedAt;
    private int durationInMinutes;
    private boolean closed = false;

    public void closeSession() {
        this.closedAt = Instant.now();
        this.closed = true;
    }
}
