package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.creator.RegisterRequestDTOCreator;
import com.honeyautomation.apiplayground.creator.UserResponseDTOCreator;
import com.honeyautomation.apiplayground.dto.response.UserResponseDTO;
import com.honeyautomation.apiplayground.exception.TestException;
import com.honeyautomation.apiplayground.exception.type.DataAlreadyUsedException;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.service.UserService;
import com.honeyautomation.apiplayground.utils.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.honeyautomation.apiplayground.creator.RegisterRequestDTOCreator.validRegisterRequestDTO;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("User controller should successfully register a new user")
    void userControllerShouldSuccessfullyRegisterNewUser() {
        doNothing().when(userServiceMock).create(any());

        final ResponseEntity<Void> response = userController.register(validRegisterRequestDTO());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("User controller should return user data successfully")
    void userControllerShouldReturnUserDataSuccessfully() {
        final UserResponseDTO userResponseDTOMock = UserResponseDTOCreator.validUserResponseDTO();
        when(userServiceMock.getUserData(any())).thenReturn(userResponseDTOMock);

        final ResponseEntity<UserResponseDTO> response = userController.search("SomeLoginToken");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userResponseDTOMock, response.getBody());
    }

    @Test
    @DisplayName("User controller should thrown ItemNotFoundException when no user was found")
    void userControllerShouldThrownItemNotFoundExceptionWhenNoUserWasFound() throws Exception {
        when(userServiceMock.getUserData(any())).thenThrow(new ItemNotFoundException(ExceptionMessages.NOT_FOUND_USER));

        final HttpHeaders httpHeaders = new HttpHeaders() {{
            set("loginToken", "SomeLoginToken");
        }};

        final MockHttpServletRequestBuilder request = get(Endpoints.REQUEST_MAPPING_USER).headers(httpHeaders);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.NOT_FOUND_USER)))
        ;
    }

    @Test
    @DisplayName("User controller should thrown internal server error when unexpected error occurs")
    void userControllerShouldThrownInternalServerErrorWhenUnexpectedErrorOccurs() throws Exception {
        doThrow(new TestException()).when(userServiceMock).create(any());

        final MockHttpServletRequestBuilder request = post(Endpoints.REQUEST_MAPPING_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(JsonParser.parse(RegisterRequestDTOCreator.validRegisterRequestDTO()));

        mockMvc.perform(request)
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.INTERNAL_SERVER_ERROR)))
        ;

    }

    @Test
    @DisplayName("User should thrown DataAlreadyUsedException")
    void userControllerShouldThrownDataAlreadyUsedException() throws Exception {
        doThrow(new DataAlreadyUsedException(List.of("email", "nickName"))).when(userServiceMock).create(any());

        final MockHttpServletRequestBuilder request = post(Endpoints.REQUEST_MAPPING_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(JsonParser.parse(RegisterRequestDTOCreator.validRegisterRequestDTO()));

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[0].field", is("email")))
                .andExpect(jsonPath("[0].message", is(ExceptionMessages.DATA_ALREADY_USED)))
                .andExpect(jsonPath("[1].field", is("nickName")))
                .andExpect(jsonPath("[1].message", is(ExceptionMessages.DATA_ALREADY_USED)))
        ;

    }
}
