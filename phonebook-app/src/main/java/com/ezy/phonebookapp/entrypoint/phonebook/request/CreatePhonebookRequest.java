package com.ezy.phonebookapp.entrypoint.phonebook.request;

import com.ezy.phonebookapp.core.domain.Phonebook;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhonebookRequest {

    @NotEmpty(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "First name must be alphabetic")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Last name must be alphabetic")
    private String lastName;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]*$", message = "Phone number must be numeric")
    private String phoneNumber;

    public Phonebook toDomain() {
        return Phonebook.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
