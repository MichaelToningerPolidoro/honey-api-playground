package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.creator.HobbyCreator;
import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.dto.response.HobbyResponseDTO;
import com.honeyautomation.apiplayground.exception.TestException;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.service.HobbyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HobbyController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class HobbyControllerTest {

    @Autowired
    private HobbyController hobbyController;
    @MockBean
    private HobbyService hobbyServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Hobby controller should return list of hobbies successfully")
    void hobbyControllerShouldReturnHobbiesWhenSuccessfully() {
        final Hobby hobbyMockData = HobbyCreator.validHobby();
        final HobbyResponseDTO responseDTOMockData = new HobbyResponseDTO(List.of(hobbyMockData));
        when(hobbyServiceMock.findAll()).thenReturn(responseDTOMockData);

        assertDoesNotThrow(() -> hobbyController.findAll(), Exception.class.getSimpleName());

        final ResponseEntity<HobbyResponseDTO> response = hobbyController.findAll();
        final HobbyResponseDTO responseBody = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responseBody);
        assertNotNull(responseBody.getHobbies());
        assertFalse(responseBody.getHobbies().isEmpty());
        assertEquals(responseBody.getHobbies().size(), responseDTOMockData.getHobbies().size());
        assertTrue(responseBody.getHobbies().contains(hobbyMockData.getHobby()));
    }

    @Test
    @DisplayName("Hobby controller should return ItemNotFoundExceptionTemplate body")
    void hobbyControllerShouldReturnItemNotFoundExceptionTemplateWhenNoHobbiesHaveBeenFound() throws Exception {
        when(hobbyServiceMock.findAll()).thenThrow(new ItemNotFoundException(ExceptionMessages.NOT_FOUND_HOBBY));

        assertThrows(ItemNotFoundException.class, () -> hobbyController.findAll());

        mockMvc.perform(get(Endpoints.REQUEST_MAPPING_HOBBY))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.NOT_FOUND_HOBBY)))
                ;
    }

    @Test
    @DisplayName("Hobby controller should thrown internal server body")
    void countryControllerShouldReturnInternalServerError() throws Exception {
        when(hobbyServiceMock.findAll()).thenThrow(new TestException());

        mockMvc.perform(get(Endpoints.REQUEST_MAPPING_HOBBY))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.INTERNAL_SERVER_ERROR)));
    }
}