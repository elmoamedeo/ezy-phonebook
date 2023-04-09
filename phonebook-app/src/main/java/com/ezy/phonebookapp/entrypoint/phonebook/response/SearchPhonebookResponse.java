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
public class SearchPhonebookResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public static SearchPhonebookResponse fromDomain(final Phonebook phonebook) {
        return SearchPhonebookResponse.builder()
                .id(phonebook.getId())
                .firstName(phonebook.getFirstName())
                .lastName(phonebook.getLastName())
                .phoneNumber(phonebook.getPhoneNumber())
                .build();
    }
}
