package com.holoview.holoview.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.holoview.holoview.model.entity.Admin;

@Service
public class TokenService {
    @Value("${security.token.secret}")
    private String secretKey;

    public String generateToken(Admin admin) {
        try {
            Algorithm algorit = Algorithm.HMAC256(secretKey);

            String token = JWT.create()
                    .withIssuer("holoview")
                    .withSubject(admin.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorit);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generating token");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorit = Algorithm.HMAC256(secretKey);

            return JWT.require(algorit)
                    .withIssuer("holoview")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}