package com.syntaxsquad.centro_treinamento.model.turma_particula;

import com.syntaxsquad.centro_treinamento.model.user.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "turma_particula",uniqueConstraints = @UniqueConstraint(columnNames = {"trainer_cpf", "aluno_cpf"}))
public class TurmaParticula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "trainer_cpf", referencedColumnName = "cpf", nullable = false)
    private User trainer;

    @ManyToOne
    @JoinColumn(name = "aluno_cpf", referencedColumnName = "cpf", nullable = false)
    private User aluno;

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public User getAluno() {
        return aluno;
    }

    public void setAluno(User aluno) {
        this.aluno = aluno;
    }
}
