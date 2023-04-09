package com.ezy.phonebookapp.core.usecase.request;

import static java.util.Optional.ofNullable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class PhonebookSearch {

    public static final int LIMIT = 30;
    public static final int OFFSET = 0;

    private final String sortBy;
    private final String sortDirection;
    private final int offset;
    private final int limit;

    public PhonebookSearch(final String sortBy, final String sortDirection, final int offset, final int limit) {
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
        this.offset = ofNullable(offset).orElse(OFFSET);
        this.limit = ofNullable(limit).orElse(LIMIT);
    }
}
