package com.syntaxsquad.centro_treinamento.model.alunos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syntaxsquad.centro_treinamento.model.alunos.Alunos;

public interface AlunoRepository extends JpaRepository<Alunos, String> {
    Optional<Alunos> findByCpf(String cpf);

}
