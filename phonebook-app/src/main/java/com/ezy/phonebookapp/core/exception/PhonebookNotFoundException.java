package com.ezy.phonebookapp.core.exception;

import static java.lang.String.format;

import lombok.Getter;

@Getter
public class PhonebookNotFoundException extends RuntimeException {

    private final Long phonebookId;

    public PhonebookNotFoundException(final Long id) {
        super(format("Phonebook with id %d not found", id));
        this.phonebookId = id;
    }
}
