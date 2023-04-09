package com.ezy.phonebookapp.core.usecase;

import static org.mockito.Mockito.*;

import com.ezy.phonebookapp.core.domain.Phonebook;
import com.ezy.phonebookapp.core.gateway.PhonebookGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeletePhonebookTest {

    @Mock
    private SearchPhonebook searchPhonebook;

    @Mock
    private PhonebookGateway phonebookGateway;

    @InjectMocks
    private DeletePhonebook deletePhonebook;

    @Test
    @DisplayName("Delete phonebook")
    void testDeletePhonebook() {
        // Arrange
        final Long id = 1L;
        final Phonebook phonebook = Phonebook.builder()
                .id(id)
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build();

        when(searchPhonebook.findById(id)).thenReturn(phonebook);

        // Act
        deletePhonebook.delete(id);

        // Assert
        verify(phonebookGateway, times(1)).delete(phonebook);
    }
}
