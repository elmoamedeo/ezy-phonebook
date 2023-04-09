package com.ezy.phonebookapp.core.usecase;

import com.ezy.phonebookapp.core.domain.Phonebook;
import com.ezy.phonebookapp.core.gateway.PhonebookGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePhonebook {

    private final SearchPhonebook searchPhonebook;
    private final PhonebookGateway phonebookGateway;

    public void delete(final Long id) {
        final Phonebook phonebookToDelete = searchPhonebook.findById(id);

        phonebookGateway.delete(phonebookToDelete);
    }
}
