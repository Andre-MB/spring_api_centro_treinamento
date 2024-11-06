package com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno;

import java.util.UUID;

public class Turma_AlunosRequest {

    private UUID turmaId;
    private String alunoCpf;

    // Getters e Setters
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
