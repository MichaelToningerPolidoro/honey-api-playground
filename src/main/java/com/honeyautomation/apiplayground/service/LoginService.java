package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.domain.User;
import com.honeyautomation.apiplayground.dto.request.LoginRequestDTO;
import com.honeyautomation.apiplayground.dto.response.LoginResponseDTO;
import com.honeyautomation.apiplayground.encoder.DefaultPasswordEncoder;
import com.honeyautomation.apiplayground.exception.type.InvalidCredentialsException;
import com.honeyautomation.apiplayground.validation.FieldValidator;
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
