package com.ezy.phonebookapp.infra.gateway.database;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ezy.phonebookapp.core.domain.Phonebook;
import com.ezy.phonebookapp.infra.gateway.database.entity.PhonebookEntity;
import com.ezy.phonebookapp.infra.repository.PhonebookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PhonebookGatewayTest {

    @Mock
    private PhonebookRepository phonebookRepository;

    @InjectMocks
    private PhonebookGatewayImpl phonebookGateway;

    @Test
    void shouldCreatePhonebook() {
        // Arrange
        final var entity = PhonebookEntity.fromDomain(Phonebook.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build());

        // Act
        when(phonebookRepository.save(any(PhonebookEntity.class))).thenReturn(entity);
        final var result = phonebookGateway.create(Phonebook.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build());

        // Assert
        assertEquals("John", result.getFirstName());
        assertEquals("1234567890", result.getPhoneNumber());
    }

    @Test
    void shouldUpdatePhonebook() {
        // Arrange
        final var entity = PhonebookEntity.fromDomain(Phonebook.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build());

        // Act
        when(phonebookRepository.save(any(PhonebookEntity.class))).thenReturn(entity);
        final var result = phonebookGateway.update(Phonebook.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build());

        // Assert
        assertEquals("John", result.getFirstName());
        assertEquals("1234567890", result.getPhoneNumber());
    }

    @Test
    void shouldDeletePhonebook() {
        // Arrange
        final var entity = PhonebookEntity.fromDomain(Phonebook.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build());

        // Act
        phonebookGateway.delete(entity.toDomain());

        // Assert
        verify(phonebookRepository).delete(entity);
    }
}
