package com.syntaxsquad.centro_treinamento.model.exercicio.exercicio_treino;

import com.syntaxsquad.centro_treinamento.model.exercicio.exercicio_treino.Exercicio_treino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Exercicio_treinoRepository extends JpaRepository<Exercicio_treino, UUID> {
}
