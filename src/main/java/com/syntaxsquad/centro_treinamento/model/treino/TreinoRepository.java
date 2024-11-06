package com.syntaxsquad.centro_treinamento.model.treino;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TreinoRepository extends JpaRepository<Treino, UUID> {
   
}
