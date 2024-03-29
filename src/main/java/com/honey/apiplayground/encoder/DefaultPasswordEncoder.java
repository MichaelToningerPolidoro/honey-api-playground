package com.honey.apiplayground.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class DefaultPasswordEncoder {

    private DefaultPasswordEncoder() {}

    // This strength value could be changed, but it is for learning purpose only ..
    private static final int STRENGTH = 10;
    private static final PasswordEncoder DEFAULT_ENCODER = new BCryptPasswordEncoder(STRENGTH);

    public static PasswordEncoder getDefaultEncoder() {
        return DEFAULT_ENCODER;
    }
}
