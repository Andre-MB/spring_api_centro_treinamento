package com.syntaxsquad.centro_treinamento.model.admin;

import java.time.LocalDate;
import java.util.UUID;

public class AdminResponse {

    private String cpf;
    private String name;
    private LocalDate birthDate;
    private UUID userId;

    public AdminResponse(String cpf, String name, LocalDate birthDate, UUID userId) {
        this.cpf = cpf;
        this.name = name;
        this.birthDate = birthDate;
        this.userId = userId;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public UUID getUserId() {
        return userId;
    }
}
