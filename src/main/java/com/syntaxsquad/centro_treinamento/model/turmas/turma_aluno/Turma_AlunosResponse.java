package com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno;

import java.util.UUID;

public class Turma_AlunosResponse {

    private UUID id;
    private UUID turmaId;
    private String alunoCpf;

    public Turma_AlunosResponse(UUID id, UUID turmaId, String alunoCpf) {
        this.id = id;
        this.turmaId = turmaId;
        this.alunoCpf = alunoCpf;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(UUID turmaId) {
        this.turmaId = turmaId;
    }

    public String getAlunoCpf() {
        return alunoCpf;
    }

    public void setAlunoCpf(String alunoCpf) {
        this.alunoCpf = alunoCpf;
    }
}
