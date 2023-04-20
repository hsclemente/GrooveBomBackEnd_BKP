package br.com.hc.groove.bom.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.hc.groove.bom.domain.models.entities.AuthUser;

@Service
public class TokenService {

    @Value("${token.secret}")
    private String secret;
    
    public String tokenFactory(AuthUser authUser) {
        try {
            return JWT.create()
                    .withIssuer("API Groove.bom")
                    .withSubject(authUser.getUsername())
                    .withClaim("id", authUser.getId())
                    .withExpiresAt(expiresAt())
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    private Instant expiresAt() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }
}
