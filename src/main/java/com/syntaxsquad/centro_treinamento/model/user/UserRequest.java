package com.syntaxsquad.centro_treinamento.model.user;

import com.syntaxsquad.centro_treinamento.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Role role; // Utilizando o enum Role

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
