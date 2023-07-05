package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.domain.User;
import com.honeyautomation.apiplayground.dto.request.LoginDTO;
import com.honeyautomation.apiplayground.encoder.DefaultPasswordEncoder;
import com.honeyautomation.apiplayground.validation.FieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    public void login(LoginDTO loginDTO) {
        FieldValidator.validate(loginDTO);
        final User userFound = userService.findUserByEmail(loginDTO.getEmail().trim());

        final boolean passwordMatches =
                DefaultPasswordEncoder.getDefaultEncoder().matches(loginDTO.getPassword(), userFound.getPassword().getPassword());

        if (!passwordMatches) {
            // thrown InvalidCredentialsException
        }

//        return token;
    }
}

