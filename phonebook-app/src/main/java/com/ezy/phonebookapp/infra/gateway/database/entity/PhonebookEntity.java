package com.ezy.phonebookapp.infra.gateway.database.entity;

import com.ezy.phonebookapp.core.domain.Phonebook;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Entity(name = "phonebook")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhonebookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.updatedAt = ZonedDateTime.now();
        this.createdAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }

    public Phonebook toDomain() {
        return Phonebook.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .phoneNumber(this.phoneNumber)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public static PhonebookEntity fromDomain(final Phonebook phonebook) {
        return PhonebookEntity.builder()
                .id(phonebook.getId())
                .firstName(phonebook.getFirstName())
                .lastName(phonebook.getLastName())
                .phoneNumber(phonebook.getPhoneNumber())
                .createdAt(phonebook.getCreatedAt())
                .updatedAt(phonebook.getUpdatedAt())
                .build();
    }
}
