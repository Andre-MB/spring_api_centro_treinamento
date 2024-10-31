package com.syntaxsquad.centro_treinamento.model.admin;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class AdminRequest {

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
    private String cpf;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Data de nascimento é obrigatória")
    private String birthDate; // Mantemos como String para receber da requisição

    @NotBlank(message = "ID do usuário é obrigatório")
    private UUID  userId;  // ID do usuário relacionado ao admin

  

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public UUID  getUserId() {
        return userId;
    }

    public void setUserId(UUID  userId) {
        this.userId = userId;
    }
}
