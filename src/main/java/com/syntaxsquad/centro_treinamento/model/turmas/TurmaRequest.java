package com.syntaxsquad.centro_treinamento.model.turmas;

import java.time.LocalDateTime;
import java.util.UUID;

public class TurmaRequest {

    private String trainerCpf;
    private UUID treinoId;
    private LocalDateTime horario;

    // Getters e Setters
    public String getTrainerCpf() {
        return trainerCpf;
    }

    public void setTrainerCpf(String trainerCpf) {
        this.trainerCpf = trainerCpf;
    }

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
}
