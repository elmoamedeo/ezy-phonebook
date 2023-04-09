package com.ezy.phonebookapp.entrypoint.exception;

import static java.lang.String.format;

import javax.validation.ValidationException;

public class SortFieldException extends ValidationException {

    public SortFieldException(final String possibleSortFields, final String sortField) {
        super(format("Sort field [%s] is invalid. List of possible values %s", sortField, possibleSortFields));
    }
}
