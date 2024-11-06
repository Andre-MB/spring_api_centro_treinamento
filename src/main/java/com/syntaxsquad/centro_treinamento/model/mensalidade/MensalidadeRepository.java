package com.syntaxsquad.centro_treinamento.model.mensalidade;

import com.syntaxsquad.centro_treinamento.model.alunos.Alunos;
import com.syntaxsquad.centro_treinamento.model.mensalidade.Mensalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, UUID> {
    List<Mensalidade> findByClientCpf(String cpf);
    List<Mensalidade> findByClient(Alunos client);
}
