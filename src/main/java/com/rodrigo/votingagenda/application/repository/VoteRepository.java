package com.rodrigo.votingagenda.application.repository;

import com.rodrigo.votingagenda.application.model.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface VoteRepository extends MongoRepository<Vote, UUID> {
    boolean existsBySessionIdAndCpf(UUID sessionId, String cpf);
}
