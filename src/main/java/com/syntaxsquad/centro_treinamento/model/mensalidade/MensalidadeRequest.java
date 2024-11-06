package com.syntaxsquad.centro_treinamento.model.mensalidade;

import java.time.LocalDate;
import java.util.UUID;

import com.syntaxsquad.centro_treinamento.model.client.Client;

import jakarta.validation.constraints.NotNull;

public class MensalidadeRequest {

    @NotNull
    private String clientId; //ID DO USERS

    @NotNull
    private LocalDate dataVencimento;

    @NotNull
    private LocalDate dataPagamento;

    @NotNull
    private Double valorMensalidade;

    @NotNull
    private String statusMensalidade;

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

    public Double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(Double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public String getStatusMensalidade() {
        return statusMensalidade;
    }

    public void setStatusMensalidade(String statusMensalidade) {
        this.statusMensalidade = statusMensalidade;
    }

    // Getters e Setters

    
}
