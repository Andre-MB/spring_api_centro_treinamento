package com.syntaxsquad.centro_treinamento.model.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.syntaxsquad.centro_treinamento.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) { // Exemplo de validação simples
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Implementação de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Define as permissões básicas do estudante
        if (role == Role.STUDENT) {
            authorities.add(new SimpleGrantedAuthority("STUDENT_PERMISSION_1"));
            authorities.add(new SimpleGrantedAuthority("STUDENT_PERMISSION_2"));
        }

        // Treinador herda permissões do estudante
        if (role == Role.TRAINER || role == Role.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("TRAINER_PERMISSION_1"));
            authorities.addAll(getStudentPermissions()); // Herdando as permissões de estudante
        }

        // Admin herda permissões de treinador e estudante
        if (role == Role.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ADMIN_PERMISSION_1"));
            authorities.addAll(getTrainerPermissions()); // Herdando as permissões de treinador
        }

        return authorities;
    }

    // Métodos auxiliares para as permissões herdadas
    private Collection<GrantedAuthority> getStudentPermissions() {
        return Arrays.asList(
            new SimpleGrantedAuthority("STUDENT_PERMISSION_1"),
            new SimpleGrantedAuthority("STUDENT_PERMISSION_2")
        );
    }

    private Collection<GrantedAuthority> getTrainerPermissions() {
        return Arrays.asList(
            new SimpleGrantedAuthority("TRAINER_PERMISSION_1"),
            new SimpleGrantedAuthority("STUDENT_PERMISSION_1"),
            new SimpleGrantedAuthority("STUDENT_PERMISSION_2")
        );
    }

    @Override
    public String getUsername() {
        // Usa o email como nome de usuário
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Aqui, true significa que a conta nunca expira. Ajuste se necessário.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // true indica que a conta nunca está bloqueada.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // As credenciais nunca expiram.
    }

    @Override
    public boolean isEnabled() {
        return true; // Define se o usuário está ativo. Modifique conforme a lógica do seu sistema.
    }
}
