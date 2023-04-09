package com.ezy.phonebookapp.entrypoint.exception;

import lombok.Getter;

public class EntrypointException extends RuntimeException {

    @Getter
    private final String errorCode;

    public EntrypointException(final String message, final String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public EntrypointException(final String message, final String errorCode, final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
