package com.syntaxsquad.centro_treinamento.model.trainer;

import com.syntaxsquad.centro_treinamento.model.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, String> {
}
