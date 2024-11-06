package com.syntaxsquad.centro_treinamento.model.exercicio;

import java.util.UUID;

public class ExercicioResponse {

    private UUID id;
    private String nomeExercicio;
    private String categoria;
    private String grupoMuscular;

    public ExercicioResponse(UUID id, String nomeExercicio, String categoria, String grupoMuscular) {
        this.id = id;
        this.nomeExercicio = nomeExercicio;
        this.categoria = categoria;
        this.grupoMuscular = grupoMuscular;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }
}
