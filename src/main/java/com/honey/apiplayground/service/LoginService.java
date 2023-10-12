package com.honey.apiplayground.service;

import com.honey.apiplayground.constants.ExceptionMessages;
import com.honey.apiplayground.domain.User;
import com.honey.apiplayground.dto.request.LoginRequestDTO;
import com.honey.apiplayground.dto.response.LoginResponseDTO;
import com.honey.apiplayground.encoder.DefaultPasswordEncoder;
import com.honey.apiplayground.exception.type.InvalidCredentialsException;
import com.honey.apiplayground.validation.FieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        FieldValidator.validate(loginRequestDTO);
        final User userFound = userService.findUserByEmail(loginRequestDTO.getEmail().trim());

        final boolean passwordMatches =
                DefaultPasswordEncoder.getDefaultEncoder().matches(loginRequestDTO.getPassword(), userFound.getPassword().getPassword());

        if (!passwordMatches) {
            throw new InvalidCredentialsException(ExceptionMessages.INVALID_USER_CREDENTIALS);
        }

        return new LoginResponseDTO(tokenService.generateToken(loginRequestDTO.getEmail()));
    }
}
