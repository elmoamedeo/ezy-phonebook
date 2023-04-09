package com.ezy.phonebookapp.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
class UpdatePhonebookTest {

    @Mock
    private SearchPhonebook searchPhonebook;

    @Mock
    private PhonebookGateway phonebookGateway;

    @InjectMocks
    private UpdatePhonebook updatePhonebook;

    @Test
    @DisplayName("Test update phonebook")
    void testUpdatePhonebook() {
        // Arrange
        final Long id = 1L;
        final Phonebook phonebook = Phonebook.builder()
                .id(id)
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build();

        when(searchPhonebook.findById(id)).thenReturn(phonebook);
        when(phonebookGateway.update(phonebook)).thenReturn(phonebook);

        // Act
        final Phonebook result = updatePhonebook.update(phonebook);

        // Assert
        assertEquals("John", result.getFirstName());
        assertEquals("1234567890", result.getPhoneNumber());

        verify(phonebookGateway, times(1)).update(phonebook);
    }

    @Test
    @DisplayName("Exception when update phonebook")
    void testExceptionWhenUpdatePhonebook() {
        // Arrange
        final Phonebook phonebook = Phonebook.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build();

        final Phonebook phonebookToUpdate = Phonebook.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build();

        when(searchPhonebook.findById(phonebook.getId())).thenReturn(phonebookToUpdate);
        when(phonebookGateway.update(phonebookToUpdate)).thenThrow(new RuntimeException());

        // Act
        final RuntimeException exception =
                assertThrows(RuntimeException.class, () -> updatePhonebook.update(phonebookToUpdate));

        // Assert
        assertEquals("java.lang.RuntimeException", exception.getClass().getName());

        verify(phonebookGateway, times(1)).update(phonebook);
    }
}
