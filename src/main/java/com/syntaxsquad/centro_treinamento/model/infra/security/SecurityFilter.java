package com.syntaxsquad.centro_treinamento.model.infra.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = this.recoverToken(request);
        
        if (token != null) {
            try {
                // Validar o token e extrair o sujeito (geralmente o email ou ID do usuário)
                String subject = tokenService.getSubject(token);
                Optional<User> optionalUser = userRepository.findByEmail(subject);
    
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get(); // Extrai o usuário do Optional
                    var authorities = user.getAuthorities(); 
    
                    // Criar a autenticação e configurar o contexto de segurança
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    // Caso o usuário não seja encontrado
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Usuário não encontrado");
                    return; // Interrompe o fluxo do filtro se o usuário não for encontrado
                }
            } catch (Exception e) {
                // Em caso de erro (token inválido ou problema na busca do usuário)
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido ou erro na autenticação");
                return; // Interrompe o fluxo do filtro se houver um erro
            }
        }
        
        // Chama o próximo filtro na cadeia
        filterChain.doFilter(request, response);
    }
    
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Retorna null se o cabeçalho não estiver presente
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
