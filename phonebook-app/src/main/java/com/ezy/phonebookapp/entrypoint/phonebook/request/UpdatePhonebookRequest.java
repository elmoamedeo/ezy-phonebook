package com.ezy.phonebookapp.entrypoint.phonebook.request;

import com.ezy.phonebookapp.core.domain.Phonebook;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePhonebookRequest {

    @Pattern(regexp = "^[a-zA-Z ]*$", message = "First name must be alphabetic")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Last name must be alphabetic")
    private String lastName;

    @Pattern(regexp = "^[0-9]*$", message = "Phone number must be numeric")
    private String phoneNumber;

    public Phonebook toDomain(final Long id) {
        return Phonebook.builder()
                .id(id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
