package com.syntaxsquad.centro_treinamento.model.turmas;

import java.time.LocalDateTime;
import java.util.UUID;

public class TurmaResponse {

    private UUID id;
    private String trainerCpf;
    private UUID treinoId;
    private LocalDateTime horario;

    public TurmaResponse(UUID id, String trainerCpf, UUID treinoId, LocalDateTime horario) {
        this.id = id;
        this.trainerCpf = trainerCpf;
        this.treinoId = treinoId;
        this.horario = horario;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
