package com.syntaxsquad.centro_treinamento.model.admin;


import com.syntaxsquad.centro_treinamento.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "admin")
public class Admin {

    
    @Column(nullable = false, unique = true)
    @Id
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
 
    @OneToOne
    @NotBlank(message = "ID do usuário é obrigatório")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Getters e Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCpf() {
        return cpf;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;

        Admin admin = (Admin) o;

        return cpf.equals(admin.cpf);
    }

    @Override
    public int hashCode() {
        return cpf.hashCode();
    }
}
