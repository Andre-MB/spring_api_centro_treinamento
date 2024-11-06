package com.syntaxsquad.centro_treinamento.model.mensalidade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.syntaxsquad.centro_treinamento.model.alunos.Alunos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "mensalidade")
public class Mensalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Use AUTO para UUID
    private UUID id;

    @ManyToOne
    @NotNull(message = "Cliente é obrigatório")
    @JoinColumn(name = "client_cpf", nullable = false)
    private Alunos client;

    @NotNull(message = "Data de vencimento é obrigatória")
    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @NotNull(message = "Data de pagamento é obrigatória")
    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @NotNull(message = "Valor da mensalidade é obrigatório")
    @Column(name = "valor_mensalidade", nullable = false)
    private Double valorMensalidade;

    @NotNull(message = "Status da mensalidade é obrigatório")
    @Column(name = "status_mensalidade", nullable = false)
    private String statusMensalidade; // pago, pendente, atrasado ou cancelado

    public Mensalidade() {
        this.dataPagamento = LocalDate.now(); // Inicializa com a data e hora atuais
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Alunos getClient() {
        return client;
    }

    public void setClient(Alunos client) {
        this.client = client;
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
}
