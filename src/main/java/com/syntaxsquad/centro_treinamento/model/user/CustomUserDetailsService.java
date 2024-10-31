package com.syntaxsquad.centro_treinamento.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username); // Alterado para usar findByEmail
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found")); // Lança exceção se não encontrado
        return user; // Supondo que User implemente UserDetails
    }
}
