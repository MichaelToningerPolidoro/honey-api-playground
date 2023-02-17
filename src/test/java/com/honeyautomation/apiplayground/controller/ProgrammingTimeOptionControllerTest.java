package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.domain.ProgrammingTimeOption;
import com.honeyautomation.apiplayground.dto.response.ProgrammingTimeOptionResponseDTO;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.factory.MockMvcFactory;
import com.honeyautomation.apiplayground.factory.ProgrammingTimeOptionFactory;
import com.honeyautomation.apiplayground.service.ProgrammingTimeOptionService;
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
public class ProgrammingTimeOptionControllerTest {

    @InjectMocks
    private ProgrammingTimeOptionController programmingTimeOptionsController;
    @Mock
    private ProgrammingTimeOptionService programmingTimeOptionService;

    @Test
    @DisplayName("Programming time option controller should return list of options successfully")
    void programmingTimeOptionControllerShouldReturnValuesSuccessfully() {
        final ProgrammingTimeOption programmingTimeOptionMockData = ProgrammingTimeOptionFactory.validProgrammingTimeOption();
        final ProgrammingTimeOptionResponseDTO responseDTOMockData = new ProgrammingTimeOptionResponseDTO(List.of(programmingTimeOptionMockData));

        when(programmingTimeOptionService.findAll()).thenReturn(responseDTOMockData);

        assertDoesNotThrow(() -> programmingTimeOptionsController.findAll(), Exception.class.getSimpleName());

        final ProgrammingTimeOptionResponseDTO responseBody = programmingTimeOptionsController.findAll().getBody();

        assertNotNull(responseBody);
        assertNotNull(responseBody.getProgrammingTimeOptions());
        assertFalse(responseBody.getProgrammingTimeOptions().isEmpty());
        assertEquals(responseDTOMockData.getProgrammingTimeOptions().size(), responseBody.getProgrammingTimeOptions().size());
        assertTrue(responseBody.getProgrammingTimeOptions().contains(programmingTimeOptionMockData.getProgrammingTime()));
    }

    @Test
    @DisplayName("Programming time option controller should return ItemNotFoundExceptionTemplate body")
    void programmingTimeOptionControllerShouldReturnItemNotFoundWhenNoOptionsHaveBeenFound() throws Exception {
        final MockMvc mockMvc = MockMvcFactory.create(programmingTimeOptionsController);

        when(programmingTimeOptionService.findAll()).thenThrow(new ItemNotFoundException(ExceptionMessages.NOT_FOUND_PROGRAMMING_TIME_OPTION));

        assertThrows(ItemNotFoundException.class, () -> programmingTimeOptionsController.findAll());

        mockMvc.perform(get(Endpoints.REQUEST_MAPPING_PROGRAMMING_TIME_OPTIONS))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.NOT_FOUND_PROGRAMMING_TIME_OPTION)))
        ;
    }
}
