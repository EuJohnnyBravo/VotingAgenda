package com.rodrigo.votingagenda.common.exception.custom;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends VotingAgendaBaseException{
    public ExternalServiceException(String message) {
        super(message, HttpStatus.valueOf(500));
    }
}
