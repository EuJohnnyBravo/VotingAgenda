package com.rodrigo.votingagenda.application.model;

import com.rodrigo.votingagenda.common.enums.Votes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "votes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    @Id
    private UUID id;
    private String sessionId;
    private String cpf;
    private Instant votedAt;
    private Votes vote;
}
