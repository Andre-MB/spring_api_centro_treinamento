package com.syntaxsquad.centro_treinamento.model.notificacoes;

public class NotificacoesRequest {

    private String titule;
    private String mensagem;

    // Getters and Setters
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
}
