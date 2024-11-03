package com.syntaxsquad.centro_treinamento.model.user;

import java.time.LocalDate;
import java.util.UUID;

public class UserResponse {

    private UUID id;
    private String email;
    private String role;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate createdAt;
    private String imageUrl;

    

    public UserResponse(UUID id, String email, String role, String name, String lastName, LocalDate birthDate,
            LocalDate createdAt, String imageUrl) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }
    public UserResponse(UUID id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
    public UserResponse(String nome , String email, String lastNome) {
        this.email = email;
        this.name = nome;
        this.lastName = lastNome;
    }
    

    public UserResponse(String email, String name, String lastName, String imageUrl, String role) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
}
