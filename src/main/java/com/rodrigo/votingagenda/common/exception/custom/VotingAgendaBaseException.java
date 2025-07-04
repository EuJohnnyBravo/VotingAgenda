package com.rodrigo.votingagenda.common.exception.custom;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
public class VotingAgendaBaseException extends RuntimeException{
    private final List<String> errors;
    private final HttpStatus httpStatus;

    public VotingAgendaBaseException(List<String> errors, HttpStatus httpStatus) {
        super(errors.getFirst());
        this.errors = errors;
        this.httpStatus = httpStatus;
    }

    public VotingAgendaBaseException(String message, HttpStatus httpStatus) {
        super(message);
        this.errors = List.of(message);
        this.httpStatus = httpStatus;
    }
}
