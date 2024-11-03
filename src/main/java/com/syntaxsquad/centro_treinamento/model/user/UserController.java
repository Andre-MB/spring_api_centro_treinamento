package com.syntaxsquad.centro_treinamento.model.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // Método para inserir um novo usuário
   

    // Método para selecionar um usuário pelo email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        UserResponse userResponse = userService.findUserByEmail(email);
        if (userResponse != null) {
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Método para atualizar o email do usuário
    @PutMapping("/email/{email}")
    public ResponseEntity<UserResponse> updateUserEmail(@PathVariable String email,
            @RequestBody UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        User user = optionalUser.get();
        user.setEmail(userRequest.getEmail());
        // Você pode também atualizar outros campos do usuário aqui, se necessário

        User updatedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse(updatedUser.getEmail(), updatedUser.getName(),
                updatedUser.getLastNome(), updatedUser.getImageUrl(), updatedUser.getRole().name());

        return ResponseEntity.ok(userResponse);
    }

    // Método para deletar um usuário pelo email
    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        userRepository.delete(optionalUser.get());
        return ResponseEntity.noContent().build();
    }

    // Método para listar todos os usuários
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream()
               .map(user -> new UserResponse(user.getEmail(), user.getName(),
                        user.getLastNome(), user.getImageUrl(), user.getRole().name()))
               .collect(Collectors.toList());
                return ResponseEntity.ok(userResponses);
    }
    
}
