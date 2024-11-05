package com.syntaxsquad.centro_treinamento.model.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @NotBlank(message = "ID é obrigatório")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Email
    @NotBlank(message = "Email é obrigatório")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Senha é obrigatório")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Cargo é obrigatório")
    @Column(nullable = false)
    private Role role;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Sobrenome é obrigatório")
    @Column(name = "last_nome", nullable = false)
    private String lastNome;
    

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve estar no passado")
    @Column(nullable = false)
    private LocalDate birthDate;

    @NotNull(message = "Data de criação é obrigatória")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @NotBlank(message = "Url de imagem é obrigatória")
    @Column(nullable = false)
    private String imageUrl;

    // Construtor padrão
    public User() {
        this.createdAt = LocalDateTime.now(); // Inicializa com a data e hora atuais
    }
    

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
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNome() {
        return lastNome;
    }

    public void setLastNome(String lastNome) {
        this.lastNome = lastNome;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Implementação de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (role == Role.STUDENT) {
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        } else if (role == Role.TRAINER) {
            authorities.add(new SimpleGrantedAuthority("ROLE_TRAINER"));
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        } else if (role == Role.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_TRAINER"));
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
