package com.syntaxsquad.centro_treinamento.model.admin;

import java.time.LocalDate;
import java.util.UUID;

public class AdminResponse {

    private String cpf;
    private UUID userId;

    public AdminResponse(String cpf,  UUID userId) {
        this.cpf = cpf;
        this.userId = userId;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    

    public UUID getUserId() {
        return userId;
    }
}
