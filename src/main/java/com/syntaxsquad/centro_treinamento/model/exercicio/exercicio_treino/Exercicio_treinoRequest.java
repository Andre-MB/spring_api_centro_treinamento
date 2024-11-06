package com.syntaxsquad.centro_treinamento.model.exercicio.exercicio_treino;

import java.time.LocalTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Exercicio_treinoRequest {

    @NotNull(message = "Exercicio ID é obrigatório")
    private UUID exercicioId;

    @NotNull(message = "Treino ID é obrigatório")
    private UUID treinoId;

    @NotNull(message = "Repetição é obrigatório")
    private Integer repeticao;

    @NotNull(message = "Serie é obrigatório")
    private Integer serie;

    @NotNull(message = "Carga é obrigatório")
    private Integer carga;

    @NotNull(message = "Descanso é obrigatório")
    private LocalTime descanso;

    // Getters e Setters
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
