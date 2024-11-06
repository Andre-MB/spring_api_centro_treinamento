package com.syntaxsquad.centro_treinamento.model.alunos;

import java.util.UUID;

public class AlunoResponse {

    private String cpf;
    private UUID userId;

    public AlunoResponse(String cpf, UUID userId) {
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
