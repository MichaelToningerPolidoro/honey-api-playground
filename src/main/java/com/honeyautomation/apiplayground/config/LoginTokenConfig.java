package com.honeyautomation.apiplayground.config;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LoginTokenConfig {

    @Value("${api.security.token.issuer}")
    private String issuer;

    @Value("${api.security.token.secret}")
    private String secretKey;

    @Value("${api.security.token.expiration.in.seconds}")
    private int tokenExpirationTimeInSeconds;

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(getSecretKey());
    }
}
