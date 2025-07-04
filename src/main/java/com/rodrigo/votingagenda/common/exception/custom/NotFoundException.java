package com.rodrigo.votingagenda.common.exception.custom;

import org.springframework.http.HttpStatus;

public class NotFoundException extends VotingAgendaBaseException{
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
