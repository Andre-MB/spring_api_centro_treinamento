package com.syntaxsquad.centro_treinamento.model.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.syntaxsquad.centro_treinamento.enums.Role;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verificar se a tabela de usuários está vazia
        if (userRepository.count() == 0) {
            // Criar o primeiro usuário admin com senha 'admin'
            User adminUser = new User();
            adminUser.setEmail("admin@gmail.com");
            adminUser.setName("Admin");
            adminUser.setCpf("12345678901");  
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setLastNome("Admin");	

            // Converter a data de nascimento para LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse("1990-01-01", formatter);
            adminUser.setBirthDate(birthDate);

            adminUser.setCreatedAt(LocalDateTime.now());
            adminUser.setRole(Role.ADMIN);

            adminUser.setImageUrl("admin.jpg");
            adminUser.setPhoneNumber("123456789");

            // Salvar o usuário admin
            userRepository.save(adminUser);

            System.out.println("Admin user created with email 'admin@gmail.com' and password 'admin'");
        }
    }
}
