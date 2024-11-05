package com.syntaxsquad.centro_treinamento.model.client;

import java.util.UUID;

public class ClientRequest {

    private String cpf;
    private UUID userId;

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
