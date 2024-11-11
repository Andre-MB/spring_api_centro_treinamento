package com.syntaxsquad.centro_treinamento.model.turma_particula;

import java.util.UUID;

public class TurmaParticulaResponse {

    private UUID id;
    private String trainer_cpf;
    private String aluno_cpf;

    
    public TurmaParticulaResponse(UUID id, String trainer_cpf, String aluno_cpf) {
        this.id = id;
        this.trainer_cpf = trainer_cpf;
        this.aluno_cpf = aluno_cpf;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getTrainer_cpf() {
        return trainer_cpf;
    }
    public void setTrainer_cpf(String trainer_cpf) {
        this.trainer_cpf = trainer_cpf;
    }
    public String getAluno_cpf() {
        return aluno_cpf;
    }
    public void setAluno_cpf(String aluno_cpf) {
        this.aluno_cpf = aluno_cpf;
    }

    
}
