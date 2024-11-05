package com.syntaxsquad.centro_treinamento.model.trainer;

import java.util.UUID;

public class TrainerResponse {

    private String cpf;
    private UUID userId;

    public TrainerResponse(String cpf, UUID userId) {
        this.cpf = cpf;
        this.userId = userId;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
