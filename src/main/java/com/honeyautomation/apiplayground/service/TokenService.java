package com.honeyautomation.apiplayground.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.honeyautomation.apiplayground.constants.General;
import com.honeyautomation.apiplayground.creator.LocalDateTimeCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.issuer}")
    private String issuer;

    @Value("${api.security.token.secret}")
    private String secretKey;

    @Value("${api.security.token.expiration}")
    private int tokenExpirationTimeInSeconds;

    public String generateToken(String email) {
        try {
            final Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(email)
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            // TODO: improve this error handling
            throw new RuntimeException("Error during login token generation", exception);
        }
    }

    public String validateToken(String token) {
        try {
            final Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            // TODO: improve this error handling
            throw new RuntimeException("Error during login token verification", exception);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTimeCreator.getToday().plusMinutes(tokenExpirationTimeInSeconds)
                .toInstant(General.STANDARD_ZONE_OFFSET);
    }
}
