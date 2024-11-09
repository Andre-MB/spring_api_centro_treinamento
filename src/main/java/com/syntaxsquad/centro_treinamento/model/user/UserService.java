package com.syntaxsquad.centro_treinamento.model.user;

import com.syntaxsquad.centro_treinamento.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   

   
    // Método que retorna o objeto completo User
    public User findUserById(String cpf) {
        return userRepository.findActiveUserByCpf(cpf)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public UserResponse findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UserResponse(user.getCpf(),
        user.getEmail(),
        user.getRole().name(),
        user.getName(),
        user.getLastNome(),
        user.getBirthDate(),
        user.getCreatedAt(),
        user.getImageUrl());
    }

 
}
