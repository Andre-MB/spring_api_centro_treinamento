package com.syntaxsquad.centro_treinamento.model;

import java.util.Date;
import java.util.UUID;

import com.syntaxsquad.centro_treinamento.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)


public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID, generator = "uuid2")
    protected UUID id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false)
    private Role role;


}
