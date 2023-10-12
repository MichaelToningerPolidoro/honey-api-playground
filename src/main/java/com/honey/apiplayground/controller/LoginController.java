package com.honey.apiplayground.controller;

import com.honey.apiplayground.constants.Endpoints;
import com.honey.apiplayground.dto.request.LoginRequestDTO;
import com.honey.apiplayground.dto.response.LoginResponseDTO;
import com.honey.apiplayground.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.REQUEST_MAPPING_LOGIN)
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return new ResponseEntity<>(loginService.login(loginRequestDTO), HttpStatus.OK);
    }
}
