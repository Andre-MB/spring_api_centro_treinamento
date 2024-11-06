package com.syntaxsquad.centro_treinamento.model.treino;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "treino")
public class Treino {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank(message = "ID é obrigatório")
    @Column(name = "id",nullable = false)
    private UUID id;

    @NotBlank(message = "Nome do treino é obrigatório")
    @Column(name = "nome_treino", nullable = false)
    private String nomeTreino;

    @NotBlank(message = "Descrição do treino é obrigatório")
    @Column(name = "descricao_treino", nullable = false)
    private String descricaoTreino;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
