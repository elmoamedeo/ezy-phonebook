package com.ezy.phonebookapp.infra.repository;

import com.ezy.phonebookapp.infra.gateway.database.entity.PhonebookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonebookRepository extends PagingAndSortingRepository<PhonebookEntity, Long> {

    Page<PhonebookEntity> findAllByLastName(String lastName, PageRequest of);
}
