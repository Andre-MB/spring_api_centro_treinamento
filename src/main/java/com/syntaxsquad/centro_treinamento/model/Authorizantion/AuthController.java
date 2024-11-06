package com.syntaxsquad.centro_treinamento.model.authorizantion;

import com.syntaxsquad.centro_treinamento.model.infra.security.TokenService;
import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;
import com.syntaxsquad.centro_treinamento.model.user.UserRequest;
import com.syntaxsquad.centro_treinamento.model.user.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    // Verifica se o email já está cadastrado
    if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    // Criptografa a senha
    String encryptedPassword = new BCryptPasswordEncoder().encode(userRequest.getPassword());

    // Converte a data de nascimento de String para LocalDate
    LocalDate birthDate;
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        birthDate = LocalDate.parse(userRequest.getBirthDate(), formatter);
    } catch (DateTimeParseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Retorna 400 se a data estiver em formato inválido
    }

    // Cria um novo usuário
    User user = new User();
    user.setEmail(userRequest.getEmail());
    user.setPassword(encryptedPassword);
    user.setName(userRequest.getName());
    user.setLastNome(userRequest.getLastNome());
    user.setBirthDate(birthDate); // Agora usando LocalDate
    user.setImageUrl(userRequest.getImageUrl());
    user.setRole(userRequest.getRole()); 
    

    // Salva o novo usuário no repositório
    userRepository.save(user);

    return ResponseEntity.status(HttpStatus.CREATED).build();
}

@GetMapping("/me")
public ResponseEntity<UserResponse> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
    // Obtém o e-mail do usuário autenticado pelo token
    String email = userDetails.getUsername();
    User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    // Cria uma resposta com as informações do usuário
    UserResponse userInfo = new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getRole().name(),
            user.getName(),
            user.getLastNome(),
            user.getBirthDate(),
            user.getCreatedAt(),
            user.getImageUrl()
    );
    return ResponseEntity.ok(userInfo);
}


}
