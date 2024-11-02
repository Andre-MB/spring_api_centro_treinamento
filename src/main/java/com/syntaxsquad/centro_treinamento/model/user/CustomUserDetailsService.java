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
        // Tenta buscar o usuário pelo email
        Optional<User> userOptional = userRepository.findByEmail(username);
        
        // Lança exceção se o usuário não for encontrado
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + username));
        
        // Retorna o usuário, que deve implementar UserDetails e estar com as permissões configuradas
        return user;
    }
}
