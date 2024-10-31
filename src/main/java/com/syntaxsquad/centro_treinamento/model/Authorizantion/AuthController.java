package com.syntaxsquad.centro_treinamento.model.Authorizantion;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
    try {
        // Autenticar o usuário
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // Se a autenticação foi bem-sucedida, gerar o token
        User user = (User) authorizationService.loadUserByUsername(loginRequest.getUsername());
        String token = authorizationService.generateToken(user);

        // Retornar o token
        return ResponseEntity.ok(token);
    } catch (Exception e) {
        // Retornar erro adequado se a autenticação falhar
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}


    // Classe interna para o Request de Login
    public static class LoginRequest {
        private String username;
        private String password;

        // Getters e Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
