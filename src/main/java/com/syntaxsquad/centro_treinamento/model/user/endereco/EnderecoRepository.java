package com.syntaxsquad.centro_treinamento.model.user.endereco;

import com.syntaxsquad.centro_treinamento.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
}
