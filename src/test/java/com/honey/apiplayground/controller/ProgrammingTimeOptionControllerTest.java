package com.honey.apiplayground.controller;

import com.honey.apiplayground.constants.Endpoints;
import com.honey.apiplayground.constants.ExceptionMessages;
import com.honey.apiplayground.creator.ProgrammingTimeOptionCreator;
import com.honey.apiplayground.domain.ProgrammingTimeOption;
import com.honey.apiplayground.dto.response.ProgrammingTimeOptionResponseDTO;
import com.honey.apiplayground.exception.TestException;
import com.honey.apiplayground.exception.type.ItemNotFoundException;
import com.honey.apiplayground.service.ProgrammingTimeOptionService;
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

@WebMvcTest(controllers = ProgrammingTimeOptionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProgrammingTimeOptionControllerTest {

    @Autowired
    private ProgrammingTimeOptionController programmingTimeOptionsController;
    @MockBean
    private ProgrammingTimeOptionService programmingTimeOptionServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Programming time option controller should return list of options successfully")
    void programmingTimeOptionControllerShouldReturnValuesSuccessfully() {
        final ProgrammingTimeOption programmingTimeOptionMockData = ProgrammingTimeOptionCreator.validProgrammingTimeOption();
        final ProgrammingTimeOptionResponseDTO responseDTOMockData = new ProgrammingTimeOptionResponseDTO(List.of(programmingTimeOptionMockData));

        when(programmingTimeOptionServiceMock.findAll()).thenReturn(responseDTOMockData);

        assertDoesNotThrow(() -> programmingTimeOptionsController.findAll(), Exception.class.getSimpleName());

        final ResponseEntity<ProgrammingTimeOptionResponseDTO> response = programmingTimeOptionsController.findAll();
        final ProgrammingTimeOptionResponseDTO responseBody = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responseBody);
        assertNotNull(responseBody.getProgrammingTimeOptions());
        assertFalse(responseBody.getProgrammingTimeOptions().isEmpty());
        assertEquals(responseDTOMockData.getProgrammingTimeOptions().size(), responseBody.getProgrammingTimeOptions().size());
        assertTrue(responseBody.getProgrammingTimeOptions().contains(programmingTimeOptionMockData.getProgrammingTime()));
    }

    @Test
    @DisplayName("Programming time option controller should return ItemNotFoundExceptionTemplate body")
    void programmingTimeOptionControllerShouldReturnItemNotFoundWhenNoOptionsHaveBeenFound() throws Exception {
        when(programmingTimeOptionServiceMock.findAll()).thenThrow(new ItemNotFoundException(ExceptionMessages.NOT_FOUND_PROGRAMMING_TIME_OPTION));

        assertThrows(ItemNotFoundException.class, () -> programmingTimeOptionsController.findAll());

        mockMvc.perform(get(Endpoints.REQUEST_MAPPING_PROGRAMMING_TIME_OPTIONS))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.NOT_FOUND_PROGRAMMING_TIME_OPTION)))
        ;
    }

    @Test
    @DisplayName("Programming time option controller should thrown internal server body")
    void countryControllerShouldReturnInternalServerError() throws Exception {
        when(programmingTimeOptionServiceMock.findAll()).thenThrow(new TestException());

        mockMvc.perform(get(Endpoints.REQUEST_MAPPING_PROGRAMMING_TIME_OPTIONS))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.INTERNAL_SERVER_ERROR)));
    }
}
