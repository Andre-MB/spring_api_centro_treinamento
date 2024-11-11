package com.syntaxsquad.centro_treinamento.model.notificacoes;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificacoesResponse {

    private UUID id;
    private String titule;
    private String mensagem;
    private LocalDateTime createdAt;

    public NotificacoesResponse(UUID id, String titule, String mensagem, LocalDateTime createdAt) {
        this.id = id;
        this.titule = titule;
        this.mensagem = mensagem;
        this.createdAt = createdAt;
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
