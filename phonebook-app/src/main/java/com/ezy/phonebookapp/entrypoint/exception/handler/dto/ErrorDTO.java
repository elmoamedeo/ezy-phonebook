package com.ezy.phonebookapp.entrypoint.exception.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorDTO {

    private String message;
    private String code;
    private String error;
    private Object cause;
    private int status;
}
