package com.syntaxsquad.centro_treinamento.model.infra.security;

import com.syntaxsquad.centro_treinamento.model.user.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private SecurityFilter SecurityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                    // Endpoints abertos
                    .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    
                    // Permissões para aluno
                    .requestMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/treino/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/alunos/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/exercicio/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/exercicio-treino/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/mensalidade/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/turmas/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/turma_alunos/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/enderecos/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/auth/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TREINER", "ROLE_ADMIN")
                    
                    // Permissões CRUD para treinador e admin
                    .requestMatchers(HttpMethod.POST, "/users/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/treino/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/treino/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/treino/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/alunos/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/alunos/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/alunos/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/exercicio/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/exercicio/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/exercicio/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/exercicio-treino/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/exercicio-treino/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/exercicio-treino/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/mensalidade/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/mensalidade/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/mensalidade/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/turmas/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/turmas/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/turmas/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/turma_alunos/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/turma_alunos/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/turma_alunos/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/enderecos/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/enderecos/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/enderecos/**").hasAnyAuthority("ROLE_TREINER", "ROLE_ADMIN")
                    
                    // Qualquer outra rota deve ser acessível apenas pelo admin
                    .anyRequest().hasAuthority("ROLE_ADMIN")
                    
                )
                .addFilterBefore(SecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
