package com.ezy.phonebookapp.entrypoint.exception;

public class InvalidSearchParamsException extends EntrypointException {

    public InvalidSearchParamsException(final String message, final String errorCode) {
        super(message, errorCode);
    }

    public InvalidSearchParamsException(final String message, final String errorCode, final Throwable cause) {
        super(message, errorCode, cause);
    }
}
