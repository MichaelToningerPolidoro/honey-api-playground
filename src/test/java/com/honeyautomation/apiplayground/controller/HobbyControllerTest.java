package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.exception.ItemNotFoundException;
import com.honeyautomation.apiplayground.factory.MockMvcFactory;
import com.honeyautomation.apiplayground.response.HobbyResponseDTO;
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

    private static final String GET_HOBBIES_ENDPOINT = "/v1/hobbies";
    private static final String EXPECTED_ITEM_NOT_FOUND_MESSAGE = "None hobby was found ...";

    @InjectMocks
    private HobbyController hobbyController;
    @Mock
    private HobbyService hobbyServiceMock;

    @Test
    @DisplayName("Hobby controller should return list of hobbies successfully")
    void hobbyControllerShouldReturnHobbiesWhenSuccessfully() {
        final String hobbyNameMockData = "Programming";
        final HobbyResponseDTO responseDTOMockData = new HobbyResponseDTO(List.of(new Hobby(1, hobbyNameMockData)));
        when(hobbyServiceMock.findAll()).thenReturn(responseDTOMockData);

        assertDoesNotThrow(() -> {
            hobbyController.findAll();
        }, Exception.class.getSimpleName());

        final HobbyResponseDTO responseBody = hobbyController.findAll().getBody();

        assertNotNull(responseBody);
        assertNotNull(responseBody.getHobbies());
        assertEquals(responseBody.getHobbies().size(), responseDTOMockData.getHobbies().size());
        assertTrue(responseBody.getHobbies().contains(hobbyNameMockData));
    }

    @Test
    @DisplayName("Hobby controller should return ItemNotFoundExceptionTemplate body")
    void hobbyControllerShouldReturnItemNotFoundExceptionTemplateWhenNoHobbyWereFound() throws Exception {
        final MockMvc mockMvc = MockMvcFactory.create(hobbyController);

        when(hobbyServiceMock.findAll()).thenThrow(new ItemNotFoundException(EXPECTED_ITEM_NOT_FOUND_MESSAGE));

        assertThrows(ItemNotFoundException.class, () -> hobbyController.findAll());

        mockMvc.perform(get(GET_HOBBIES_ENDPOINT))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(EXPECTED_ITEM_NOT_FOUND_MESSAGE)))
                ;
    }
}