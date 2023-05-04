package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.honeyautomation.apiplayground.creator.RegisterRequestDTOCreator.validRegisterRequestDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userServiceMock;

    @Test
    @DisplayName("User controller should successfully register a new user")
    void userControllerShouldSuccessfullyRegisterNewUser() {
        doNothing().when(userServiceMock).create(any());

        final ResponseEntity<Void> response = userController.register(validRegisterRequestDTO());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
    }
}
