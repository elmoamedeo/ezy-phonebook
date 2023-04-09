package com.ezy.phonebookapp.entrypoint.exception;

import javax.validation.ValidationException;

public class SortDirectionException extends ValidationException {

    public SortDirectionException(final String sortDirection) {
        super(String.format("Sort direction [%s] is invalid. Possible values are [asc, desc]", sortDirection));
    }
}
