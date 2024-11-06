package com.syntaxsquad.centro_treinamento.model.exercicio;

import com.syntaxsquad.centro_treinamento.model.exercicio.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExercicioRepository extends JpaRepository<Exercicio, UUID> {
}
