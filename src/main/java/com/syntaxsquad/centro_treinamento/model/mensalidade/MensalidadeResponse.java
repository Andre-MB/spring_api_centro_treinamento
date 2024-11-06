package com.syntaxsquad.centro_treinamento.model.mensalidade;

import java.time.LocalDate;
import java.util.UUID;

public class MensalidadeResponse {
    private UUID id;
    private String clientId;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double valorMensalidade;
    private String statusMensalidade;

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public String getStatusMensalidade() {
        return statusMensalidade;
    }

    public void setStatusMensalidade(String statusMensalidade) {
        this.statusMensalidade = statusMensalidade;
    }
}
