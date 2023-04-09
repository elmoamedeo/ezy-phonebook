package com.ezy.phonebookapp.entrypoint.phonebook.request;

import com.ezy.phonebookapp.core.usecase.request.PhonebookSearch;
import java.beans.ConstructorProperties;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchPhonebookRequest {

    private String sortBy;
    private String sortDirection;
    private Integer offset;
    private Integer limit;

    @ConstructorProperties({"sortBy", "sortDirection", "offset", "limit"})
    public SearchPhonebookRequest(
            final String sortBy, final String sortDirection, final Integer offset, final Integer limit) {
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
        this.offset = offset;
        this.limit = limit;
    }

    public PhonebookSearch toDomain() {
        if (this.sortBy == null) {
            sortBy = "id";
        }

        if (this.sortDirection == null) {
            sortDirection = "asc";
        }

        return PhonebookSearch.builder()
                .sortBy(this.sortBy)
                .sortDirection(this.sortDirection)
                .offset(this.offset)
                .limit(this.limit)
                .build();
    }
}
