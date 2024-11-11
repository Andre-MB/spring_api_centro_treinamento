package com.syntaxsquad.centro_treinamento.model.notificacoes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notificacoes")
public class Notificacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "titule", nullable = false)
    private String titule;

    @Column(name = "mensagem", nullable = false)
    private String mensagem;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Método para definir o valor de createdAt antes de persistir a entidade
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitule() {
        return titule;
    }

    public void setTitule(String titule) {
        this.titule = titule;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
