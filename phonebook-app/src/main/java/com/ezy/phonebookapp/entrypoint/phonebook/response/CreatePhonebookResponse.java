package com.ezy.phonebookapp.entrypoint.phonebook.response;

import com.ezy.phonebookapp.core.domain.Phonebook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhonebookResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public static CreatePhonebookResponse fromDomain(final Phonebook phonebook) {
        return CreatePhonebookResponse.builder()
                .id(phonebook.getId())
                .firstName(phonebook.getFirstName())
                .lastName(phonebook.getLastName())
                .phoneNumber(phonebook.getPhoneNumber())
                .build();
    }
}
