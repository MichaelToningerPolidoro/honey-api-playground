package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.creator.MockMvcCreator;
import com.honeyautomation.apiplayground.creator.RegisterRequestDTOCreator;
import com.honeyautomation.apiplayground.exception.TestException;
import com.honeyautomation.apiplayground.service.UserService;
import com.honeyautomation.apiplayground.utils.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.honeyautomation.apiplayground.creator.RegisterRequestDTOCreator.validRegisterRequestDTO;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    @DisplayName("User controller should thrown internal server error when unexpected error occurs")
    void userControllerShouldThrownInternalServerErrorWhenUnexpectedErrorOccurs() throws Exception {
        final MockMvc mockMvc = MockMvcCreator.create(userController);

        doThrow(new TestException()).when(userServiceMock).create(any());

        final MockHttpServletRequestBuilder request = post(Endpoints.REQUEST_MAPPING_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonParser.parse(RegisterRequestDTOCreator.validRegisterRequestDTO()));

        mockMvc.perform(request)
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.INTERNAL_SERVER_ERROR)))
        ;

    }
}
