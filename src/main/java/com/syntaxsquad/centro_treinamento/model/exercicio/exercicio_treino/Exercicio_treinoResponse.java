package com.syntaxsquad.centro_treinamento.model.exercicio.exercicio_treino;

import java.time.LocalTime;
import java.util.UUID;

public class Exercicio_treinoResponse {

    private UUID id;
    private UUID exercicioId;
    private UUID treinoId;
    private Integer repeticao;
    private Integer serie;
    private Integer carga;
    private LocalTime descanso;

    public Exercicio_treinoResponse(UUID id, UUID exercicioId, UUID treinoId, Integer repeticao, Integer serie, Integer carga, LocalTime descanso) {
        this.id = id;
        this.exercicioId = exercicioId;
        this.treinoId = treinoId;
        this.repeticao = repeticao;
        this.serie = serie;
        this.carga = carga;
        this.descanso = descanso;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getExercicioId() {
        return exercicioId;
    }

    public void setExercicioId(UUID exercicioId) {
        this.exercicioId = exercicioId;
    }

    public UUID getTreinoId() {
        return treinoId;
    }

    public void setTreinoId(UUID treinoId) {
        this.treinoId = treinoId;
    }

    public Integer getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(Integer repeticao) {
        this.repeticao = repeticao;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Integer getCarga() {
        return carga;
    }

    public void setCarga(Integer carga) {
        this.carga = carga;
    }

    public LocalTime getDescanso() {
        return descanso;
    }

    public void setDescanso(LocalTime descanso) {
        this.descanso = descanso;
    }
}
