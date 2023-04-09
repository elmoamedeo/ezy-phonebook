package com.ezy.phonebookapp.core.usecase;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.ezy.phonebookapp.core.domain.Phonebook;
import com.ezy.phonebookapp.core.exception.PhonebookNotFoundException;
import com.ezy.phonebookapp.core.gateway.PhonebookGateway;
import com.ezy.phonebookapp.core.usecase.request.PhonebookSearch;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchPhonebookTest {

    @Mock
    private PhonebookGateway phonebookGateway;

    @InjectMocks
    private SearchPhonebook searchPhonebook;

    @Test
    @DisplayName("Test search phonebook")
    void testSearchPhonebook() {
        // Arrange
        final PhonebookSearch phonebookSearch = PhonebookSearch.builder()
                .sortBy("id")
                .sortDirection("asc")
                .offset(0)
                .limit(10)
                .build();

        // Act
        searchPhonebook.search(phonebookSearch);

        // Assert
        verify(phonebookGateway, times(1)).search(phonebookSearch);
    }

    @Test
    @DisplayName("Test search phonebook by id")
    void testSearchPhonebookById() {
        // Arrange
        final Long id = 1L;

        final Phonebook phonebook = Phonebook.builder()
                .id(id)
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build();

        when(phonebookGateway.findById(id)).thenReturn(Optional.ofNullable(phonebook));

        // Act
        searchPhonebook.findById(id);

        // Assert
        verify(phonebookGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test search phonebook by id not found")
    void testSearchPhonebookNotFound() {
        // Arrange
        final Long id = 1L;

        when(phonebookGateway.findById(id)).thenReturn(Optional.empty());

        // Act
        assertThrows(PhonebookNotFoundException.class, () -> searchPhonebook.findById(id));

        // Assert
        verify(phonebookGateway, times(1)).findById(id);
    }
}
