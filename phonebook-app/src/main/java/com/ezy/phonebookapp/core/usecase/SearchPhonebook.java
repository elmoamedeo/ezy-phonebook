package com.ezy.phonebookapp.core.usecase;

import com.ezy.phonebookapp.core.domain.Phonebook;
import com.ezy.phonebookapp.core.exception.PhonebookNotFoundException;
import com.ezy.phonebookapp.core.gateway.PhonebookGateway;
import com.ezy.phonebookapp.core.usecase.request.PhonebookSearch;
import com.ezy.phonebookapp.utils.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchPhonebook {

    private final PhonebookGateway phonebookGateway;

    public Pagination<Phonebook> search(final PhonebookSearch search) {
        return phonebookGateway.search(search);
    }

    public Phonebook findById(final Long id) {
        return phonebookGateway.findById(id).orElseThrow(() -> new PhonebookNotFoundException(id));
    }
}
