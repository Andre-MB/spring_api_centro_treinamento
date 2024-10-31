package com.syntaxsquad.centro_treinamento.model.user;

import com.syntaxsquad.centro_treinamento.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(hashPassword(userRequest.getPassword())); // Hash da senha
        user.setRole(Role.valueOf(userRequest.getRole().toUpperCase())); // Use a enum correta

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getRole().name());
    }

    // Método que retorna o objeto completo User
    public User findUserById(UUID userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public UserResponse findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UserResponse(user.getId(), user.getEmail(), user.getRole().name());
    }

    private String hashPassword(String password) {
        // Implemente a lógica para hash da senha
        return password; // Mude para a lógica de hash apropriada
    }
}
