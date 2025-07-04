package com.rodrigo.votingagenda.application.model;

import com.rodrigo.votingagenda.common.exception.custom.InvalidVoteException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Document(collection = "sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    private UUID id;
    private UUID agendaId;
    private Set<Vote> votes = new HashSet<>();
    private Instant createdAt;
    private Instant closedAt;
    private int durationInMinutes;
    private boolean isClosed = false;

    public void setVotes(Vote vote){
        if(this.votes.stream().anyMatch(v -> v.getCpf().equals(vote.getCpf()))){
            throw new InvalidVoteException("CPF: " + vote.getCpf() + " já votou");
        }

        if(vote.getVotedAt().isAfter(this.createdAt.plusSeconds(this.durationInMinutes * 60L))){
            this.isClosed = true;
            throw new InvalidVoteException("Sessão fechada: " + vote.getSessionId());
        }
        this.votes.add(vote);
    }
}
