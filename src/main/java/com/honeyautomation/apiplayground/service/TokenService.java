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

    public void validateLoginToken(String token) {
        String subject;

        try {
            subject = getLoginSubject(token);
        } catch (JWTVerificationException exception) {
            throw new InvalidLoginTokenException(ExceptionMessages.INVALID_LOGIN_TOKEN);
        }

        final boolean isTokenValid = subject != null && !subject.isBlank();

        if (!isTokenValid) {
            throw new InvalidLoginTokenException(ExceptionMessages.INVALID_LOGIN_TOKEN);
        }
    }

    public String getLoginSubject(String token) {
        return JWT.require(loginTokenConfig.getAlgorithm())
                .withIssuer(loginTokenConfig.getIssuer())
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant generateExpirationDate() {
        return LocalDateTimeCreator.getToday().plusSeconds(loginTokenConfig.getTokenExpirationTimeInSeconds())
                .toInstant(General.STANDARD_ZONE_OFFSET);
    }
}
