package com.syntaxsquad.centro_treinamento.model.client;

import java.util.UUID;

public class ClientResponse {

    private String cpf;
    private UUID userId;

    public ClientResponse(String cpf, UUID userId) {
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
