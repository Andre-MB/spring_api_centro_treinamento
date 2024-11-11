package com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno;

import java.util.UUID;
import com.syntaxsquad.centro_treinamento.model.turmas.Turma;
import com.syntaxsquad.centro_treinamento.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "turma_alunos" , uniqueConstraints = @UniqueConstraint(columnNames = {"turma_id", "aluno_cpf"}))
public class Turma_Alunos {

    @Id
    @NotBlank(message = "ID é obrigatório")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "truma_id é obrigatório")
    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @NotBlank(message = "aluno_cpf é obrigatório")
    @ManyToOne
    @JoinColumn(name = "aluno_cpf", nullable = false)
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    
}
