package com.syntaxsquad.centro_treinamento.model.turmas;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.UUID;

public interface TurmaRepository extends JpaRepository<Turma, UUID> {
    boolean existsByUser_CpfAndHorario(String cpf, LocalDateTime horario);
}
