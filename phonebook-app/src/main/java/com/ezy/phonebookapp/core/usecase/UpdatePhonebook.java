package com.ezy.phonebookapp.core.usecase;

import com.ezy.phonebookapp.core.domain.Phonebook;
import com.ezy.phonebookapp.core.gateway.PhonebookGateway;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePhonebook {

    private final PhonebookGateway phonebookGateway;
    private final SearchPhonebook searchPhonebook;

    @Transactional
    public Phonebook update(final Phonebook phonebook) {
        final Phonebook phonebookToUpdate = searchPhonebook.findById(phonebook.getId());

        final Phonebook updatedPhonebook = phonebookToUpdate.toBuilder()
                .firstName(phonebook.getFirstName())
                .lastName(phonebook.getLastName())
                .phoneNumber(phonebook.getPhoneNumber())
                .build();

        return phonebookGateway.update(updatedPhonebook);
    }
}
