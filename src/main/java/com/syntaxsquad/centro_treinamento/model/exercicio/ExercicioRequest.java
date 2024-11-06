package com.syntaxsquad.centro_treinamento.model.exercicio;

import jakarta.validation.constraints.NotBlank;

public class ExercicioRequest {

    @NotBlank(message = "Nome do exercício é obrigatório")
    private String nomeExercicio;

    @NotBlank(message = "Categoria é obrigatório")
    private String categoria;

    @NotBlank(message = "Grupo muscular é obrigatório")
    private String grupoMuscular;

    // Getters e Setters
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
