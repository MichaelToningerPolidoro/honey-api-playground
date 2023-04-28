package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.creator.HobbyCreator;
import com.honeyautomation.apiplayground.creator.MockMvcCreator;
import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.dto.response.HobbyResponseDTO;
import com.honeyautomation.apiplayground.exception.TestException;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.service.HobbyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
class HobbyControllerTest {

    @InjectMocks
    private HobbyController hobbyController;
    @Mock
    private HobbyService hobbyServiceMock;

    @Test
    @DisplayName("Hobby controller should return list of hobbies successfully")
    void hobbyControllerShouldReturnHobbiesWhenSuccessfully() {
        final Hobby hobbyMockData = HobbyCreator.validHobby();
        final HobbyResponseDTO responseDTOMockData = new HobbyResponseDTO(List.of(hobbyMockData));
        when(hobbyServiceMock.findAll()).thenReturn(responseDTOMockData);

        assertDoesNotThrow(() -> hobbyController.findAll(), Exception.class.getSimpleName());

        final HobbyResponseDTO responseBody = hobbyController.findAll().getBody();

        assertNotNull(responseBody);
        assertNotNull(responseBody.getHobbies());
        assertFalse(responseBody.getHobbies().isEmpty());
        assertEquals(responseBody.getHobbies().size(), responseDTOMockData.getHobbies().size());
        assertTrue(responseBody.getHobbies().contains(hobbyMockData.getHobby()));
    }

    @Test
    @DisplayName("Hobby controller should return ItemNotFoundExceptionTemplate body")
    void hobbyControllerShouldReturnItemNotFoundExceptionTemplateWhenNoHobbiesHaveBeenFound() throws Exception {
        final MockMvc mockMvc = MockMvcCreator.create(hobbyController);

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
        final MockMvc mockMvc = MockMvcCreator.create(hobbyController);

        when(hobbyServiceMock.findAll()).thenThrow(new TestException());

        mockMvc.perform(get(Endpoints.REQUEST_MAPPING_HOBBY))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.INTERNAL_SERVER_ERROR)));
    }
}