package com.ezy.phonebookapp.core.domain;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Phonebook {

    Long id;
    String firstName;
    String lastName;
    String phoneNumber;

    @EqualsAndHashCode.Exclude
    ZonedDateTime updatedAt;

    @EqualsAndHashCode.Exclude
    ZonedDateTime createdAt;
}
