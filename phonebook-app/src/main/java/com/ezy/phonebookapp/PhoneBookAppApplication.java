package com.ezy.phonebookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class PhoneBookAppApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PhoneBookAppApplication.class, args);
    }
}
