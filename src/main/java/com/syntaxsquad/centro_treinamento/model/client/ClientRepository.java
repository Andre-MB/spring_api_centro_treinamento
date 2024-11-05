package com.syntaxsquad.centro_treinamento.model.client;

import com.syntaxsquad.centro_treinamento.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
