package com.syntaxsquad.centro_treinamento.model.exercicio.exercicio_treino;

import java.time.LocalTime;
import java.util.Timer;
import java.util.UUID;

import com.syntaxsquad.centro_treinamento.model.exercicio.Exercicio;
import com.syntaxsquad.centro_treinamento.model.treino.Treino;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "exercicio_treino")
public class Exercicio_treino {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "exercicio_id", nullable = false)
    private Exercicio exercicio;

    @ManyToOne
    @JoinColumn(name = "treino_id", nullable = false)
    private Treino treino;

    @NotBlank(message = "Repetição é obrigatório")
    @Column(name = "repeticao", nullable = false)
    private Integer repeticao;

    @NotBlank(message = "Serie é obrigatório")
    @Column(name = "serie", nullable = false)
    private Integer serie;

    @NotBlank(message = "Carga é obrigatório")
    @Column(name = "carga", nullable = false)
    private Integer carga;

    @NotBlank(message = "Descanso é obrigatório")
    @Column(name = "descanso", nullable = false)
    private LocalTime descanso;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
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
