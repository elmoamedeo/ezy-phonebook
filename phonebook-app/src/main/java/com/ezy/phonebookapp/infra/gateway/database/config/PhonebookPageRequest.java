package com.ezy.phonebookapp.infra.gateway.database.config;

import com.ezy.phonebookapp.entrypoint.exception.InvalidSearchParamsException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class PhonebookPageRequest implements Pageable {

    private final int offset;
    private final int page;
    private final int limit;
    private final Sort sort;

    private PhonebookPageRequest(final int offset, final int page, final int limit, final Sort sort) {
        if (offset < 0) {
            throw new InvalidSearchParamsException(
                    "Offset must be greater than or equal to 0", "INVALID_SEARCH_PARAMS");
        }
        if (limit < 0) {
            throw new InvalidSearchParamsException("Limit must be greater than or equal to 0", "INVALID_SEARCH_PARAMS");
        }
        this.offset = offset;
        this.page = page;
        this.limit = limit;
        this.sort = sort;
    }

    public static PhonebookPageRequest of(final int offset, final int page, final int limit) {
        return new PhonebookPageRequest(offset, page, limit, Sort.by(Sort.Direction.ASC, "id"));
    }

    public static PhonebookPageRequest of(final int offset, final int limit, final Sort sort) {
        return new PhonebookPageRequest(offset, 0, limit, sort);
    }

    @Override
    public int getPageNumber() {
        return this.page;
    }

    @Override
    public int getPageSize() {
        return this.limit;
    }

    @Override
    public long getOffset() {
        return this.offset + ((long) this.page * this.limit);
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public Pageable next() {
        return of(this.offset, this.page + 1, this.limit);
    }

    @Override
    public Pageable previousOrFirst() {
        return of(this.offset, this.page - 1, this.limit);
    }

    @Override
    public Pageable first() {
        return of(this.offset, 0, this.limit);
    }

    @Override
    public Pageable withPage(final int pageNumber) {
        return Pageable.unpaged();
    }

    @Override
    public boolean hasPrevious() {
        return page > 0;
    }
}
