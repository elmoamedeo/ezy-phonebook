package com.ezy.phonebookapp.core.usecase;

import com.ezy.phonebookapp.core.domain.Phonebook;
import com.ezy.phonebookapp.core.gateway.PhonebookGateway;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePhonebook {

    private final PhonebookGateway phonebookGateway;

    @Transactional
    public Phonebook create(final Phonebook phonebook) {
        return phonebookGateway.create(phonebook);
    }
}
