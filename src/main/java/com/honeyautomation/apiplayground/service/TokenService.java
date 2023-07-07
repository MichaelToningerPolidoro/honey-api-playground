package com.honeyautomation.apiplayground.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.constants.General;
import com.honeyautomation.apiplayground.creator.LocalDateTimeCreator;
import com.honeyautomation.apiplayground.exception.type.InvalidLoginTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.issuer}")
    private String issuer;

    @Value("${api.security.token.secret}")
    private String secretKey;

    @Value("${api.security.token.expiration.in.seconds}")
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
            throw new RuntimeException(ExceptionMessages.LOGIN_TOKEN_GENERATION, exception);
        }
    }

    public boolean isLoginTokenValid(String token) {
        try {
            final Algorithm algorithm = Algorithm.HMAC256(secretKey);
            final String subject = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();

            return subject != null && !subject.isBlank();

        } catch (JWTVerificationException exception) {
            throw new InvalidLoginTokenException(ExceptionMessages.INVALID_LOGIN_TOKEN);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTimeCreator.getToday().plusSeconds(tokenExpirationTimeInSeconds)
                .toInstant(General.STANDARD_ZONE_OFFSET);
    }
}
