package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;
import com.honeyautomation.apiplayground.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.REQUEST_MAPPING_USER)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        userService.create(registerRequestDTO);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    // TODO: Change return type here to a DTO
    @GetMapping
    public ResponseEntity<String> search(@RequestHeader String loginToken) {
        userService.getUserData(loginToken);
        return new ResponseEntity<>("Finded", HttpStatus.OK);
    }
}
