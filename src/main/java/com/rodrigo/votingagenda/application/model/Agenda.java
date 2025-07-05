package com.rodrigo.votingagenda.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
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
    private Instant createdAt;
    private Instant updatedAt;
}
