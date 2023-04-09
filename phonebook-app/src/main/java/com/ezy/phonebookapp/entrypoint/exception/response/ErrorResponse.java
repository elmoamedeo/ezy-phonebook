package com.ezy.phonebookapp.entrypoint.exception.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ErrorResponse {

    int status;
    String message;
    String error;
    Throwable cause;

    public ErrorResponse(final int status, final String message, final String error, final Throwable cause) {
        this.status = status;
        this.message = message;
        this.error = error;
        this.cause = cause;
    }

    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return HttpStatus.valueOf(this.getStatus());
    }
}
