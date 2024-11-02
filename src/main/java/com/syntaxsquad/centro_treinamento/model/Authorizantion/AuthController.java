package com.syntaxsquad.centro_treinamento.model.Authorizantion;

import com.syntaxsquad.centro_treinamento.model.infra.security.TokenService;
import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;
import com.syntaxsquad.centro_treinamento.model.user.UserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                authRequest.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        // Gere o token usando o email do usuário autenticado
        var token = tokenService.generateToken(authRequest.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRequest.getPassword());
        User newUser = new User();
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(userRequest.getRole());
        userRepository.save(newUser);
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
