package com.ezy.phonebookapp.infra.gateway.database;

import static com.ezy.phonebookapp.infra.gateway.database.entity.PhonebookEntity.fromDomain;

import com.ezy.phonebookapp.core.domain.Phonebook;
import com.ezy.phonebookapp.core.gateway.PhonebookGateway;
import com.ezy.phonebookapp.core.usecase.request.PhonebookSearch;
import com.ezy.phonebookapp.infra.gateway.database.config.PhonebookPageRequest;
import com.ezy.phonebookapp.infra.gateway.database.entity.PhonebookEntity;
import com.ezy.phonebookapp.infra.repository.PhonebookRepository;
import com.ezy.phonebookapp.utils.Pagination;
import com.ezy.phonebookapp.utils.Paging;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhonebookGatewayImpl implements PhonebookGateway {

    private final PhonebookRepository phonebookRepository;

    @Override
    public Phonebook create(final Phonebook phonebook) {
        return phonebookRepository.save(fromDomain(phonebook)).toDomain();
    }

    @Override
    public Phonebook update(final Phonebook phonebook) {
        return phonebookRepository.save(fromDomain(phonebook)).toDomain();
    }

    @Override
    public Optional<Phonebook> findById(final Long id) {
        return phonebookRepository.findById(id).map(PhonebookEntity::toDomain);
    }

    @Override
    public Pagination<Phonebook> search(final PhonebookSearch search) {
        final Sort sort = Sort.by(Sort.Direction.fromString(search.getSortDirection()), search.getSortBy());
        final Pageable pageable = PhonebookPageRequest.of(search.getOffset(), search.getLimit(), sort);
        final Page<PhonebookEntity> phonebookEntityPage = phonebookRepository.findAll(pageable);
        return getPhonebookPage(search, phonebookEntityPage);
    }

    @Override
    public void delete(final Phonebook phonebook) {
        phonebookRepository.delete(fromDomain(phonebook));
    }

    private Pagination<Phonebook> getPhonebookPage(
            final PhonebookSearch search, final Page<PhonebookEntity> phonebookEntityPage) {
        return Pagination.<Phonebook>builder()
                .paging(Paging.builder()
                        .total(phonebookEntityPage.getTotalElements())
                        .limit(search.getLimit())
                        .offset(search.getOffset())
                        .build())
                .results(phonebookEntityPage.getContent().stream()
                        .map(PhonebookEntity::toDomain)
                        .collect(Collectors.toList()))
                .build();
    }
}
