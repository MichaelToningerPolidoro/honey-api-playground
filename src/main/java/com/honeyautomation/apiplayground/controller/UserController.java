package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;
import com.honeyautomation.apiplayground.dto.request.UpdateUserRequestDTO;
import com.honeyautomation.apiplayground.dto.response.UserResponseDTO;
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
