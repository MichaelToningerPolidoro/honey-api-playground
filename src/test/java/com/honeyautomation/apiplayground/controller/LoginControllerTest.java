package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.creator.LoginRequestDTOCreator;
import com.honeyautomation.apiplayground.dto.response.LoginResponseDTO;
import com.honeyautomation.apiplayground.exception.type.InvalidCredentialsException;
import com.honeyautomation.apiplayground.service.LoginService;
import com.honeyautomation.apiplayground.utils.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.nio.charset.StandardCharsets;

import static com.honeyautomation.apiplayground.creator.LoginRequestDTOCreator.validLoginRequestDTO;
import static com.honeyautomation.apiplayground.creator.LoginResponseDTOCreator.validLoginResponseDTO;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LoginController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    @MockBean
    private LoginService loginService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Login Service should return a login token")
    void loginServiceShouldReturnALoginToken() {
        when(loginService.login(any())).thenReturn(validLoginResponseDTO());

        final ResponseEntity<LoginResponseDTO> response = loginController.login(validLoginRequestDTO());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getToken());
        assertFalse(response.getBody().getToken().isBlank());
    }


    @Test
    @DisplayName("Login controller should thrown InvalidCredentialsException")
    void loginControllerShouldThrownInvalidCredentialsException() throws Exception {
        when(loginService.login(any())).thenThrow(new InvalidCredentialsException(ExceptionMessages.INVALID_USER_CREDENTIALS));

        final MockHttpServletRequestBuilder request = post(Endpoints.REQUEST_MAPPING_LOGIN)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(JsonParser.parse(LoginRequestDTOCreator.invalidLoginRequestDTO()));

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.INVALID_USER_CREDENTIALS)))
        ;
    }
}
