package com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno;

import com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno.Turma_Alunos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Turma_AlunosRepository extends JpaRepository<Turma_Alunos, UUID> {
}
