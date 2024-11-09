package com.syntaxsquad.centro_treinamento.model.user;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserResponse {

    private String cpf;
    private String email;
    private String role;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
    private String imageUrl;


   
    // Construtor com todos os campos
    public UserResponse(String cpf, String email, String role, String name, String lastName, LocalDate birthDate, LocalDateTime createdAt, String imageUrl) {
        this.cpf = cpf;
        this.email = email;
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }


    

    // Getters e Setters
    

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }




    public String getCpf() {
        return cpf;
    }




    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    
}
