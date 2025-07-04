package com.rodrigo.votingagenda.common.exception.custom;

import org.springframework.http.HttpStatus;

public class InvalidVoteException extends VotingAgendaBaseException{
    public InvalidVoteException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
