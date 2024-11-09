package com.syntaxsquad.centro_treinamento.model.turmas;

import java.time.LocalDateTime;
import java.util.UUID;

public class TurmaRequest {

    private String trainer_Cpf;
    private UUID treinoId;
    private LocalDateTime horario;

    // Getters e Setters
   

    public UUID getTreinoId() {
        return treinoId;
    }

    public void setTreinoId(UUID treinoId) {
        this.treinoId = treinoId;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getTrainer_Cpf() {
        return trainer_Cpf;
    }

    public void setTrainer_Cpf(String trainer_Cpf) {
        this.trainer_Cpf = trainer_Cpf;
    }
}
