package com.rodrigo.votingagenda.contract.exception.response;

import java.time.LocalDateTime;
import java.util.List;

public record ExceptionResponse(
        LocalDateTime timeStamp,
        List<String> errors
) {
    public ExceptionResponse(List<String> errors) {
        this(LocalDateTime.now(), errors);
    }
}