package com.ezy.phonebookapp.entrypoint.phonebook;

import com.ezy.phonebookapp.core.domain.Phonebook;
import com.ezy.phonebookapp.core.usecase.CreatePhonebook;
import com.ezy.phonebookapp.core.usecase.DeletePhonebook;
import com.ezy.phonebookapp.core.usecase.SearchPhonebook;
import com.ezy.phonebookapp.core.usecase.UpdatePhonebook;
import com.ezy.phonebookapp.entrypoint.phonebook.request.CreatePhonebookRequest;
import com.ezy.phonebookapp.entrypoint.phonebook.request.SearchPhonebookRequest;
import com.ezy.phonebookapp.entrypoint.phonebook.request.UpdatePhonebookRequest;
import com.ezy.phonebookapp.entrypoint.phonebook.response.CreatePhonebookResponse;
import com.ezy.phonebookapp.entrypoint.phonebook.response.SearchPhonebookResponse;
import com.ezy.phonebookapp.entrypoint.phonebook.response.UpdatePhonebookResponse;
import com.ezy.phonebookapp.entrypoint.validation.sortby.ValidSortBy;
import com.ezy.phonebookapp.entrypoint.validation.sortdirection.ValidSortDirection;
import com.ezy.phonebookapp.utils.Pagination;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phonebooks")
@AllArgsConstructor
@CrossOrigin
@SuppressWarnings("PMD.ExcessiveImports")
@Validated
public class PhonebookController {

    private final CreatePhonebook createPhonebook;
    private final UpdatePhonebook updatePhonebook;
    private final SearchPhonebook searchPhonebook;
    private final DeletePhonebook deletePhonebook;

    @PostMapping("/create")
    public ResponseEntity<CreatePhonebookResponse> post(@RequestBody @Valid final CreatePhonebookRequest request) {
        final Phonebook phonebook = createPhonebook.create(request.toDomain());
        return new ResponseEntity<>(CreatePhonebookResponse.fromDomain(phonebook), HttpStatus.CREATED);
    }

    @PutMapping("/{phonebookId}/update")
    public ResponseEntity<UpdatePhonebookResponse> put(
            @PathVariable("phonebookId") final Long phonebookId,
            @RequestBody @Valid final UpdatePhonebookRequest request) {
        final Phonebook phonebook = updatePhonebook.update(request.toDomain(phonebookId));
        return new ResponseEntity<>(UpdatePhonebookResponse.fromDomain(phonebook), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Pagination<SearchPhonebookResponse>> get(
            @RequestParam(value = "sortBy", required = false) @ValidSortBy final String sortBy,
            @RequestParam(value = "sortDirection", required = false) @ValidSortDirection final String sortDirection,
            @RequestParam(value = "offset", required = false, defaultValue = "0") final Integer offset,
            @RequestParam(value = "limit", required = false, defaultValue = "10") final Integer limit) {
        final SearchPhonebookRequest request = SearchPhonebookRequest.builder()
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .offset(offset)
                .limit(limit)
                .build();
        final Pagination<Phonebook> phonebooks = searchPhonebook.search(request.toDomain());
        return new ResponseEntity<>(
                Pagination.<SearchPhonebookResponse>builder()
                        .paging(phonebooks.getPaging())
                        .results(phonebooks.getResults().stream()
                                .map(SearchPhonebookResponse::fromDomain)
                                .collect(Collectors.toList()))
                        .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{phonebookId}/delete")
    public ResponseEntity<Void> delete(@PathVariable("phonebookId") final Long phonebookId) {
        deletePhonebook.delete(phonebookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
