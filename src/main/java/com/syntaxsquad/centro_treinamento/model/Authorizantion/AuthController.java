package com.syntaxsquad.centro_treinamento.model.authorizantion;

import com.syntaxsquad.centro_treinamento.model.email.EmailService;
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

// Validador de CPF
class CPFValidator {

    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false; // Verifica se o CPF tem 11 dígitos numéricos
        }

        int[] cpfArray = new int[11];
        for (int i = 0; i < 11; i++) {
            cpfArray[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += cpfArray[i] * (10 - i);
        }
        int digit1 = (sum1 * 10) % 11;
        if (digit1 == 10 || digit1 == 11) {
            digit1 = 0;
        }

        if (cpfArray[9] != digit1) {
            return false; // Se o primeiro dígito verificador estiver errado
        }

        int sum2 = 0;
        for (int i = 0; i < 10; i++) {
            sum2 += cpfArray[i] * (11 - i);
        }
        int digit2 = (sum2 * 10) % 11;
        if (digit2 == 10 || digit2 == 11) {
            digit2 = 0;
        }

        return cpfArray[10] == digit2; // Se o segundo dígito verificador estiver errado
    }
}

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    private final EmailService emailService;

    @Autowired
    public AuthController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                authRequest.getPassword());
                
        var auth = this.authenticationManager.authenticate(usernamePassword);
        

        // Gere o token usando o email do usuário autenticado
        var token = tokenService.generateToken(authRequest.getEmail());
         //email que foi feito login
      
         emailService.sendEmail(authRequest.getEmail(), "Login realizado com sucesso!", "Olá, " + authRequest.getEmail() + "! Seu login foi realizado com sucesso.");

        return ResponseEntity.ok(new AuthResponse(token));
       
    }

   @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRequest userRequest) {
        // Verifica se o CPF é válido
        if (!CPFValidator.isValid(userRequest.getCpf())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400 se o CPF for inválido
        }

        // Verifica se o email já está cadastrado
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Retorna 409 se o email já estiver registrado
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
        user.setCpf(userRequest.getCpf());
        user.setEmail(userRequest.getEmail());
        user.setPassword(encryptedPassword);
        user.setName(userRequest.getName());
        user.setLastNome(userRequest.getLastNome());
        user.setBirthDate(birthDate); // Agora usando LocalDate
        user.setImageUrl(userRequest.getImageUrl());
        user.setRole(userRequest.getRole()); 

        // Salva o novo usuário no repositório
        userRepository.save(user);

        // Envia um e-mail de confirmação
     
        String msg = "Seja bem-vindo ao nosso centro de treinamento!\n" +
        "Seu cadastro foi realizado com sucesso.\n" +
        "SEU LOGIN: " + userRequest.getEmail() + "\n" +
        "SUA SENHA: " + userRequest.getPassword();

        emailService.sendEmail(userRequest.getEmail(), "Bem-vindo ao Centro Treinamento",msg);	


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        // Obtém o e-mail do usuário autenticado pelo token
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Cria uma resposta com as informações do usuário
        UserResponse userInfo = new UserResponse(
                user.getCpf(),
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
