package com.syntaxsquad.centro_treinamento.model.notificacoes;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface NotificacoesRepository extends JpaRepository<Notificacoes, UUID> {
}