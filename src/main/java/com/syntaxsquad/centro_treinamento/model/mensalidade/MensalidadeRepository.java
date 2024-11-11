package com.syntaxsquad.centro_treinamento.model.mensalidade;

import com.syntaxsquad.centro_treinamento.model.mensalidade.Mensalidade;
import com.syntaxsquad.centro_treinamento.model.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, UUID> {

 
    List<Mensalidade> findByUserCpf(String cpf);
    List<Mensalidade> findByStatusMensalidade(String status);
  
}

