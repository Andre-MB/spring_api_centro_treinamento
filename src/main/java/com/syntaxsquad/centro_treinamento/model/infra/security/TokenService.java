package com.syntaxsquad.centro_treinamento.model.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 h 
                .sign(algorithm);
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getSubject();
    }
}
