package com.honeyautomation.apiplayground.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.honeyautomation.apiplayground.config.LoginTokenConfig;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.constants.General;
import com.honeyautomation.apiplayground.creator.LocalDateTimeCreator;
import com.honeyautomation.apiplayground.exception.type.InvalidLoginTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Autowired
    private LoginTokenConfig loginTokenConfig;

    public String generateToken(String email) {
        try {
            return JWT.create()
                    .withIssuer(loginTokenConfig.getIssuer())
                    .withSubject(email)
                    .withExpiresAt(generateExpirationDate())
                    .sign(loginTokenConfig.getAlgorithm());

        } catch (JWTCreationException exception) {
            throw new RuntimeException(ExceptionMessages.LOGIN_TOKEN_GENERATION, exception);
        }
    }

    public boolean isLoginTokenValid(String token) {
        try {
            final String subject = JWT.require(loginTokenConfig.getAlgorithm())
                    .withIssuer(loginTokenConfig.getIssuer())
                    .build()
                    .verify(token)
                    .getSubject();

            return subject != null && !subject.isBlank();

        } catch (JWTVerificationException exception) {
            throw new InvalidLoginTokenException(ExceptionMessages.INVALID_LOGIN_TOKEN);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTimeCreator.getToday().plusSeconds(loginTokenConfig.getTokenExpirationTimeInSeconds())
                .toInstant(General.STANDARD_ZONE_OFFSET);
    }
}
