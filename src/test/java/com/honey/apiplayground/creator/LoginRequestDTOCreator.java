package com.honey.apiplayground.creator;

import com.honey.apiplayground.dto.request.LoginRequestDTO;

public final class LoginRequestDTOCreator {

    private LoginRequestDTOCreator() {}
    
    public static LoginRequestDTO validLoginRequestDTO() {
        return LoginRequestDTO.builder()
                .email(getValidEmail())
                .password(getValidPassword())
                .build();
    }
    
    public static LoginRequestDTO invalidLoginRequestDTO() {
        return LoginRequestDTO.builder()
                .email(null)
                .password(getValidPassword())
                .build();
    }

    public static LoginRequestDTO invalidPasswordLoginRequestDTO() {
        return LoginRequestDTO.builder()
                .email(getValidEmail())
                .password(getInvalidPassword())
                .build();
    }

    private static String getValidEmail() {
        return "validemail@testing.com";
    }
    
    private static String getValidPassword() {
        return "ValidPassword";
    }

    private static String getInvalidPassword() {
        return "123987MyInvalidPassword987321";
    }
}
