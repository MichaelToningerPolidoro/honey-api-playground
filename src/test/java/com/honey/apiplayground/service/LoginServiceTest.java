package com.honey.apiplayground.service;

import com.honey.apiplayground.dto.request.LoginRequestDTO;
import com.honey.apiplayground.dto.response.LoginResponseDTO;
import com.honey.apiplayground.exception.type.InvalidCredentialsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static com.honey.apiplayground.creator.LoginRequestDTOCreator.*;
import static com.honey.apiplayground.creator.UserCreator.validUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private UserService userService;

    @Mock
    private TokenService tokenService;

    @Test
    @DisplayName("Login should return token successfully")
    void loginShouldReturnTokenSuccessfully() {
        final LoginRequestDTO loginRequestDTO = validLoginRequestDTO();

        when(userService.findUserByEmail(any())).thenReturn(validUser());
        when(tokenService.generateToken(any())).thenReturn("SomeToken");

        final LoginResponseDTO loginResponseDTO = loginService.login(loginRequestDTO);

        assertNotNull(loginResponseDTO);
        assertNotNull(loginResponseDTO.getToken());
        assertFalse(loginResponseDTO.getToken().isEmpty());
    }

    @Test
    @DisplayName("Login with invalid data should throw ConstraintViolationException")
    void loginWithInvalidDataShouldThrowConstraintViolationException() {
        assertThrows(ConstraintViolationException.class, () -> loginService.login(invalidLoginRequestDTO()));
    }

    @Test
    @DisplayName("Login should thrown InvalidCredentialsException when passwords doesn't match")
    void loginShouldThrownInvalidCredentialsException() {
        when(userService.findUserByEmail(any())).thenReturn(validUser());

        assertThrows(InvalidCredentialsException.class, () -> loginService.login(invalidPasswordLoginRequestDTO()));
    }
}
