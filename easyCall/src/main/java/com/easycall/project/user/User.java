package com.easycall.project.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String address;
    private String phone;
    private LocalDate dateOfBirth;
    private String role;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
