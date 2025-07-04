package com.rodrigo.votingagenda.application.repository;

import com.rodrigo.votingagenda.application.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface SessionRepository extends MongoRepository<Session, UUID> {
    List<Session> findByIsClosedFalse();

}
