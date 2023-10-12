package com.honey.apiplayground.controller;

import com.honey.apiplayground.constants.Endpoints;
import com.honey.apiplayground.dto.request.RegisterRequestDTO;
import com.honey.apiplayground.dto.request.UpdateUserRequestDTO;
import com.honey.apiplayground.dto.response.UserResponseDTO;
import com.honey.apiplayground.service.UserService;
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

    @GetMapping
    public ResponseEntity<UserResponseDTO> search(@RequestHeader String loginToken) {
        return new ResponseEntity<>(userService.getUserData(loginToken), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Void> update(@RequestHeader String loginToken, @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        userService.update(loginToken, updateUserRequestDTO);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestHeader String loginToken) {
        userService.delete(loginToken);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
