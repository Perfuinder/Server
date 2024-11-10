package com.swu.perfuinder.config.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final ErrorResponseStatus status;

    public BadRequestException(ErrorResponseStatus status) {
        this.status = status;
    }

}

