package com.syntaxsquad.centro_treinamento.model.turmas;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syntaxsquad.centro_treinamento.model.treino.Treino;
import com.syntaxsquad.centro_treinamento.model.user.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "turmas")
public class Turma {

    // A chave primária da turma (UUID gerado automaticamente)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Relacionamento Many-to-One com Trainer (Treinador)
    @ManyToOne
    @JoinColumn(name = "trainer_id",nullable = false)
    private User user;

    // Relacionamento Many-to-One com Treino
    @ManyToOne
    @JoinColumn(name = "treino_cpf",nullable = false)
    private Treino treino;

    // Horário da turma (com a validação de não ser nulo)
    @NotBlank(message = "Horário é obrigatório")
    @Column(name = "horario", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // Formatação da data no formato ISO 8601
    private LocalDateTime horario;

    // Método para garantir a geração do UUID antes de persistir
    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }   
    
    public void setUser(User user) {
        this.user = user;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}
