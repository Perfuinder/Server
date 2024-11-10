package com.swu.perfuinder.config.exception;

import lombok.Getter;

@Getter
public class DatabaseException extends RuntimeException {
    private final ErrorResponseStatus status;

    public DatabaseException(ErrorResponseStatus status) {
        this.status = status;
    }

}

