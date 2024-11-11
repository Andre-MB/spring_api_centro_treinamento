package com.syntaxsquad.centro_treinamento.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findActiveUserByCpf(String cpf);

    // Método customizado para encontrar usuários com o papel "STUDENT"
    @Query("SELECT u FROM User u WHERE u.role = 2 ")
    List<User> findAllAlunos();
}
