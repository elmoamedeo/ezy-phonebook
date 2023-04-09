package com.ezy.phonebookapp.core.gateway;

import com.ezy.phonebookapp.core.domain.Phonebook;
import com.ezy.phonebookapp.core.usecase.request.PhonebookSearch;
import com.ezy.phonebookapp.utils.Pagination;
import java.util.Optional;

public interface PhonebookGateway {

    Phonebook create(Phonebook phonebook);

    Phonebook update(Phonebook phonebook);

    Optional<Phonebook> findById(Long id);

    Pagination<Phonebook> search(PhonebookSearch search);

    void delete(Phonebook phonebook);
}
