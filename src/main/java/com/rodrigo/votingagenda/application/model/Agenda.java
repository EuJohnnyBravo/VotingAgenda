package com.rodrigo.votingagenda.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Document(collection = "agendas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {
    @Id
    private UUID id;
    private String title;
    private String description;
    private List<Session> sessions;
    private Instant createdAt;
    private Instant updatedAt;
}
