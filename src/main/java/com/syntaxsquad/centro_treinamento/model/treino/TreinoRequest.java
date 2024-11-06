package com.syntaxsquad.centro_treinamento.model.treino;

import jakarta.validation.constraints.NotBlank;

public class TreinoRequest {

    @NotBlank(message = "Nome do treino é obrigatório")
    private String nomeTreino;

    @NotBlank(message = "Descrição do treino é obrigatória")
    private String descricaoTreino;

    // Getters and Setters
    public String getNomeTreino() {
        return nomeTreino;
    }

    public void setNomeTreino(String nomeTreino) {
        this.nomeTreino = nomeTreino;
    }

    public String getDescricaoTreino() {
        return descricaoTreino;
    }

    public void setDescricaoTreino(String descricaoTreino) {
        this.descricaoTreino = descricaoTreino;
    }
}
