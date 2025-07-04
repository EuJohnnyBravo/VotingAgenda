package com.rodrigo.votingagenda.common.exception;

import com.rodrigo.votingagenda.common.exception.custom.VotingAgendaBaseException;
import com.rodrigo.votingagenda.contract.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VotingAgendaBaseException.class)
    public ResponseEntity<ExceptionResponse> handleVotingAgendaBaseException(VotingAgendaBaseException ex){
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new ExceptionResponse(ex.getErrors()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneric(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(List.of("Erro interno do servidor", ex.getMessage())));
    }
}
