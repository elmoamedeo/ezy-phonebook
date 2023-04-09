package com.ezy.phonebookapp.entrypoint.exception.handler;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.ezy.phonebookapp.core.exception.PhonebookNotFoundException;
import com.ezy.phonebookapp.entrypoint.exception.EntrypointException;
import com.ezy.phonebookapp.entrypoint.exception.handler.dto.ErrorDTO;
import com.ezy.phonebookapp.entrypoint.exception.response.ErrorResponse;
import java.util.Objects;
import javax.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class ControllerExceptionHandler {

    @ExceptionHandler(EntrypointException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handleEntrypointException(final EntrypointException exception) {
        return ErrorDTO.builder()
                .message(exception.getMessage())
                .code(exception.getErrorCode())
                .cause(exception.getCause())
                .status(BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException exception) {
        final String exceptionMessage = format("Entry [%s] not valid.", exception.getObjectName());

        final var errorResponse = new ErrorResponse(
                BAD_REQUEST.value(),
                exceptionMessage,
                Objects.requireNonNull(exception.getFieldError()).toString(),
                exception.getCause());

        return new ResponseEntity<>(errorResponse, null, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            final MethodArgumentTypeMismatchException exception) {
        final String exceptionMessage = format("Property [%s] type mismatch.", exception.getName());

        final var errorResponse = new ErrorResponse(BAD_REQUEST.value(), exceptionMessage, null, exception.getCause());

        return new ResponseEntity<>(errorResponse, null, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(PhonebookNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handlePhonebookNotFoundException(
            final PhonebookNotFoundException exception) {
        final String exceptionMessage = format("Phonebook with id %d not found", exception.getPhonebookId());

        final var errorResponse = new ErrorResponse(BAD_REQUEST.value(), exceptionMessage, null, exception.getCause());

        return new ResponseEntity<>(errorResponse, null, NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ErrorResponse> handleValidationException(final ValidationException exception) {
        final String exceptionMessage = format("Validation failed for %s", exception.getClass());

        final var errorResponse = new ErrorResponse(
                BAD_REQUEST.value(),
                exception.getCause() == null ? null : exception.getCause().getMessage(),
                exceptionMessage,
                null);

        return new ResponseEntity<>(errorResponse, null, errorResponse.getHttpStatus());
    }
}
