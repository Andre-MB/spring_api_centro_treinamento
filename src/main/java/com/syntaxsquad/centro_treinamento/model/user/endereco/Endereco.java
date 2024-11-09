package com.syntaxsquad.centro_treinamento.model.user.endereco;

import java.util.UUID;

import com.syntaxsquad.centro_treinamento.model.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "endereco")
public class Endereco {


    @Id
    @NotBlank(message = "ID é obrigatório")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @NotBlank(message = "CEP é obrigatório")
    @Column(name = "cep", nullable = false)
    private String cep;


    @NotBlank(message = "Logradouro é obrigatório")
    @Column(name = "logradouro", nullable = false)
    private String logradouro;


    @NotBlank(message = "Bairro é obrigatório")
    @Column(name = "bairro", nullable = false)
    private String bairro;


    @NotBlank(message = "Cidade é obrigatório")
    @Column(name = "cidade", nullable = false)
    private String cidade;


    @NotBlank(message = "Estado é obrigatório")
    @Column(name = "estado", nullable = false)
    private String estado;


    @NotBlank(message = "Pais é obrigatório")
    @Column(name = "pais", nullable = false)
    private String pais;


    @NotBlank(message = "Complemento é obrigatório")
    @Column(name = "complemento", nullable = false)
    private String complemento;

    @ManyToOne
    @NotBlank(message = "ID do usuário é obrigatório")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public String getCep() {
        return cep;
    }


    public void setCep(String cep) {
        this.cep = cep;
    }


    public String getLogradouro() {
        return logradouro;
    }


    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }


    public String getBairro() {
        return bairro;
    }


    public void setBairro(String bairro) {
        this.bairro = bairro;
    }


    public String getCidade() {
        return cidade;
    }


    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getPais() {
        return pais;
    }


    public void setPais(String pais) {
        this.pais = pais;
    }


    public String getComplemento() {
        return complemento;
    }


    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    

    



    

}
