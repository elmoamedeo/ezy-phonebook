package com.ezy.phonebookapp.entrypoint.phonebook;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezy.phonebookapp.core.domain.Phonebook;
import com.ezy.phonebookapp.core.exception.PhonebookNotFoundException;
import com.ezy.phonebookapp.core.usecase.CreatePhonebook;
import com.ezy.phonebookapp.core.usecase.DeletePhonebook;
import com.ezy.phonebookapp.core.usecase.SearchPhonebook;
import com.ezy.phonebookapp.core.usecase.UpdatePhonebook;
import com.ezy.phonebookapp.entrypoint.exception.handler.ControllerExceptionHandler;
import com.ezy.phonebookapp.entrypoint.phonebook.request.CreatePhonebookRequest;
import com.ezy.phonebookapp.entrypoint.phonebook.request.SearchPhonebookRequest;
import com.ezy.phonebookapp.entrypoint.phonebook.request.UpdatePhonebookRequest;
import com.ezy.phonebookapp.utils.Pagination;
import com.ezy.phonebookapp.utils.Paging;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@WebAppConfiguration
@ContextConfiguration(classes = {PhonebookController.class, ControllerExceptionHandler.class})
class PhonebookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreatePhonebook createPhonebook;

    @MockBean
    private UpdatePhonebook updatePhonebook;

    @MockBean
    private SearchPhonebook searchPhonebook;

    @MockBean
    private DeletePhonebook deletePhonebook;

    @Test
    void testCreatePhonebook() throws Exception {
        // Arrange
        final Phonebook phonebook = Phonebook.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("+1234567890")
                .build();

        // Act
        final CreatePhonebookRequest request = new CreatePhonebookRequest("John", "Doe", "1234567890");
        when(createPhonebook.create(any())).thenReturn(phonebook);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/phonebooks/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("+1234567890"));
    }

    @Test
    void testCreatePhonebookWithInvalidRequest() throws Exception {
        // Arrange
        final CreatePhonebookRequest request = new CreatePhonebookRequest(null, "Doe", "1234567890");

        // Act
        when(createPhonebook.create(any())).thenThrow(ConstraintViolationException.class);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/phonebooks/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdatePhonebook() throws Exception {
        // Arrange
        final Phonebook phonebook = Phonebook.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("+1234567890")
                .build();

        // Act
        final UpdatePhonebookRequest request = new UpdatePhonebookRequest("John", "Doe", "1234567890");
        when(updatePhonebook.update(any())).thenReturn(phonebook);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/phonebooks/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("+1234567890"));
    }

    @Test
    void testUpdatePhonebookWithInvalidRequest() throws Exception {
        // Arrange
        final UpdatePhonebookRequest request = new UpdatePhonebookRequest("123", "Doe", "1234567890");

        // Act
        when(updatePhonebook.update(any())).thenThrow(ConstraintViolationException.class);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/phonebooks/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdatePhonebookWithInvalidId() throws Exception {
        // Arrange
        final UpdatePhonebookRequest request = new UpdatePhonebookRequest("John", "Doe", "1234567890");

        // Act
        when(updatePhonebook.update(any())).thenThrow(PhonebookNotFoundException.class);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/phonebooks/1000/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSearchPhonebook() throws Exception {
        // Arrange
        final Phonebook phonebook = Phonebook.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("+1234567890")
                .build();

        // Act
        final SearchPhonebookRequest request = new SearchPhonebookRequest("id", "asc", 0, 10);
        when(searchPhonebook.search(any()))
                .thenReturn(Pagination.<Phonebook>builder()
                        .paging(Paging.builder().total(10).offset(0).limit(10).build())
                        .results(Collections.singletonList(phonebook))
                        .build());

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/phonebooks/search?sort=id&order=asc&offset=0&limit=10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].id").value(1L))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.results[0].firstName").value("John"))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.results[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].phoneNumber")
                        .value("+1234567890"));
    }

    @Test
    void testSearchPhonebookWithInvalidRequest() throws Exception {
        // Arrange
        final SearchPhonebookRequest request = new SearchPhonebookRequest("idd", "asc", 0, 10);

        // Act
        when(searchPhonebook.search(any())).thenThrow(ConstraintViolationException.class);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/phonebooks/search?sort=idd&order=asc&offset=0&limit=10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeletePhonebook() throws Exception {
        // Arrange
        doNothing().when(deletePhonebook).delete(any());

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/phonebooks/1/delete")).andExpect(status().isNoContent());
    }

    @Test
    void testDeletePhonebookWithInvalidId() throws Exception {
        // Arrange
        doThrow(PhonebookNotFoundException.class).when(deletePhonebook).delete(any());

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/phonebooks/1000/delete"))
                .andExpect(status().isNotFound());
    }
}
