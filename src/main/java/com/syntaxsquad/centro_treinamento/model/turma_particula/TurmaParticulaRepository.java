package com.syntaxsquad.centro_treinamento.model.turma_particula;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TurmaParticulaRepository extends JpaRepository<TurmaParticula, UUID> {
    boolean existsByTrainer_CpfAndAluno_Cpf(String trainerCpf, String alunoCpf);
}
