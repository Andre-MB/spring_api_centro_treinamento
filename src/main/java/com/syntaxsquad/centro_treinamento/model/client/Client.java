package com.syntaxsquad.centro_treinamento.model.client;



import com.syntaxsquad.centro_treinamento.model.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "client")
public class Client  {

    @Id
    @NotBlank(message = "CPF é obrigatório")
    @Column(name = "cpf", nullable = false, unique = true) 
    private String cpf;

    @OneToOne
    @NotBlank(message = "ID do usuário é obrigatório")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
