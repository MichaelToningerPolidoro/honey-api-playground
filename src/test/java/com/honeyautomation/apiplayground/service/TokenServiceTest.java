package com.honeyautomation.apiplayground.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.honeyautomation.apiplayground.config.LoginTokenConfig;
import com.honeyautomation.apiplayground.exception.type.InvalidLoginTokenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private LoginTokenConfig loginTokenConfigMock;

    private final String standardIssuer = "testingIssuer@email.com";
    private final String standardSecretKey = "my-default-honey-secret-key";
    private final int standardExpirationTime = 600;
    private final Algorithm standardAlgorithm = Algorithm.HMAC256(standardSecretKey);
    private final Algorithm voidSecretKeyAlgorithm = Algorithm.HMAC256("");

    @Test
    @DisplayName("Token should be generated successfully")
    void tokenShouldBeGeneratedSuccessfully() {
        when(loginTokenConfigMock.getIssuer()).thenReturn(standardIssuer);
        when(loginTokenConfigMock.getTokenExpirationTimeInSeconds()).thenReturn(standardExpirationTime);
        when(loginTokenConfigMock.getAlgorithm()).thenReturn(standardAlgorithm);

        assertDoesNotThrow(() -> {
            final String token = tokenService.generateToken(standardIssuer);
            assertNotNull(token);
            assertFalse(token.isBlank());
        });
    }

    @Test
    @DisplayName("TokenService should thrown exception when algorithm secret key is empty")
    void tokenServiceShouldThrownExceptionWhenAlgorithmIsNull() {
        when(loginTokenConfigMock.getIssuer()).thenReturn(standardIssuer);
        when(loginTokenConfigMock.getTokenExpirationTimeInSeconds()).thenReturn(standardExpirationTime);
        when(loginTokenConfigMock.getAlgorithm()).thenReturn(voidSecretKeyAlgorithm);

        assertThrows(IllegalArgumentException.class, () -> tokenService.generateToken(standardIssuer));
    }

    @Test
    @DisplayName("TokenService should validate token successfully")
    void tokenServiceShouldValidateTokenSuccessfully() {
        when(loginTokenConfigMock.getIssuer()).thenReturn(standardIssuer);
        when(loginTokenConfigMock.getSecretKey()).thenReturn(standardSecretKey);
        when(loginTokenConfigMock.getTokenExpirationTimeInSeconds()).thenReturn(standardExpirationTime);
        when(loginTokenConfigMock.getAlgorithm()).thenReturn(standardAlgorithm);

        final String token = tokenService.generateToken(standardIssuer);

        assertDoesNotThrow(() -> tokenService.isLoginTokenValid(token));
    }

    @Test
    @DisplayName("TokenService should thrown exception when token is invalid")
    void tokenServiceShouldThrownExceptionWhenTokenIsInvalid() {
        when(loginTokenConfigMock.getIssuer()).thenReturn(standardIssuer);
        when(loginTokenConfigMock.getSecretKey()).thenReturn(standardSecretKey);
        when(loginTokenConfigMock.getTokenExpirationTimeInSeconds()).thenReturn(standardExpirationTime);
        when(loginTokenConfigMock.getAlgorithm()).thenReturn(standardAlgorithm);

        final String invalidToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJob25leS1hcGktcGxheWdyb3VuZCIsInN1YiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTY5MDAyNjc3NH0.ScGoAeZCgjDMHv9grkkbEXKEROhGK0WnV0daJZ2xOhU";

        assertThrows(InvalidLoginTokenException.class, () -> tokenService.isLoginTokenValid(invalidToken));
    }


}
