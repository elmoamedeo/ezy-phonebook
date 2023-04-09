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
class CreatePhonebookTest {

    @Mock
    private PhonebookGateway phonebookGateway;

    @InjectMocks
    private CreatePhonebook createPhonebook;

    @Test
    @DisplayName("Test create phonebook")
    void testCreatePhonebook() {
        // Arrange
        final Phonebook phonebook = Phonebook.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build();

        when(phonebookGateway.create(phonebook)).thenReturn(phonebook);

        // Act
        final Phonebook result = createPhonebook.create(phonebook);

        // Assert
        assertEquals("John", result.getFirstName());
        assertEquals("1234567890", result.getPhoneNumber());

        verify(phonebookGateway, times(1)).create(phonebook);
    }

    @Test
    @DisplayName("Test exception when create phonebook")
    void testExceptionWhenCreatePhonebook() {
        // Arrange
        final Phonebook phonebook = Phonebook.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .build();

        when(phonebookGateway.create(phonebook)).thenThrow(new RuntimeException());

        // Act
        final RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            createPhonebook.create(phonebook);
        });

        // Assert
        assertEquals("java.lang.RuntimeException", exception.getClass().getName());

        verify(phonebookGateway, times(1)).create(phonebook);
    }
}
