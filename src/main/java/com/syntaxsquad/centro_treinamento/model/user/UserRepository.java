package com.syntaxsquad.centro_treinamento.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
 
    Optional<User> findByEmail(String email); 

   
    Optional<User> findActiveUserById(UUID id);

    

}

