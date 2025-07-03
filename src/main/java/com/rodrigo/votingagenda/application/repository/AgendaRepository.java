package com.rodrigo.votingagenda.application.repository;

import com.rodrigo.votingagenda.application.model.Agenda;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AgendaRepository extends MongoRepository<Agenda, UUID> {
}
