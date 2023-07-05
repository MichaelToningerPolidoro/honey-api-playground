package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.dto.request.LoginDTO;
import com.honeyautomation.apiplayground.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    // TODO: adjust endpoint
    @PostMapping("/v1/login")
    public void login(@RequestBody LoginDTO loginDTO) {
        // TODO: Return JWT returned from loginService
        //  return ResponseEntity<LoginResponseDTO> returning {"token":"tokenHere"} status 200
        loginService.login(loginDTO);
    }
}
