package com.syntaxsquad.centro_treinamento.model.turmas;

import com.syntaxsquad.centro_treinamento.model.turmas.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TurmaRepository extends JpaRepository<Turma, UUID> {
}
