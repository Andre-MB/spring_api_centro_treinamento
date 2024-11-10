package com.syntaxsquad.centro_treinamento.model.ficha_de_avaliacao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syntaxsquad.centro_treinamento.model.user.User;

@Repository
public interface FichaAvaliacaoRepository extends JpaRepository<FichaAvaliacao, Long> {
    Optional<FichaAvaliacao> findByUser(User user);
}