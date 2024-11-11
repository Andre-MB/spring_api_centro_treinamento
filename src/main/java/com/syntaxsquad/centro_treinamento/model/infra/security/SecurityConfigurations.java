package com.syntaxsquad.centro_treinamento.model.infra.security;

import com.syntaxsquad.centro_treinamento.model.user.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                    // Endpoints abertos
                    .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                    // Permissões para aluno (role aluno, trainer, e admin)
                    .requestMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/treinos/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/alunos/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/exercicios/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/exercicio-treino/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/mensalidade/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/turmas/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/turma-alunos**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/enderecos/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/auth/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/FichaAvaliacao/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/noticacoes/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/turma-particulas/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_TRAINER", "ROLE_ADMIN")

                    // Permissões CRUD para treinador e admin
                    .requestMatchers(HttpMethod.POST, "/users/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/treinos/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/treinos/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/treinos/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/alunos/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/alunos/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/alunos/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/exercicios/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/exercicios/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/exercicios/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/exercicio-treino/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/exercicio-treino/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/exercicio-treino/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/mensalidades/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/mensalidades/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/mensalidades/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/turmas/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/turmas/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/turmas/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/turma-alunos**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/turma-alunos**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/turma-alunos**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/enderecos/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/enderecos/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/enderecos/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/FichaAvaliacao/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/FichaAvaliacao/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/FichaAvaliacao/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/turma-particulas/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/turma-particulas/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/turma-particulas/**").hasAnyAuthority("ROLE_TRAINER", "ROLE_ADMIN")

                    // Qualquer outra rota deve ser acessível apenas pelo admin
                    .anyRequest().hasAuthority("ROLE_ADMIN")
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
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
