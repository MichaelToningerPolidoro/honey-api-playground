package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.creator.CountryCreator;
import com.honeyautomation.apiplayground.domain.Country;
import com.honeyautomation.apiplayground.dto.response.CountryResponseDTO;
import com.honeyautomation.apiplayground.exception.TestException;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.service.CountryService;
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

@WebMvcTest(controllers = CountryController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CountryControllerTest {

    @Autowired
    private CountryController countryController;

    @MockBean
    private CountryService countryServiceMock;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Country controller should return list of hobbies successfully")
    void countryControllerShouldReturnCountriesWhenSuccessfully() {
        final Country countryDataMock = CountryCreator.validCountry();
        final CountryResponseDTO responseDTOMockData = new CountryResponseDTO(List.of(countryDataMock));
        when(countryServiceMock.findAll()).thenReturn(responseDTOMockData);

        assertDoesNotThrow(() -> countryController.findAll(), ItemNotFoundException.class.getSimpleName());

        final ResponseEntity<CountryResponseDTO> response = countryController.findAll();
        final CountryResponseDTO responseBody = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responseBody);
        assertNotNull(responseBody.getCountries());
        assertFalse(responseBody.getCountries().isEmpty());
        assertEquals(responseBody.getCountries().size(), responseDTOMockData.getCountries().size());
        assertTrue(responseBody.getCountries().contains(countryDataMock.getCountry()));
    }

    @Test
    @DisplayName("Country controller should return ItemNotFoundExceptionTemplate body")
    void countryControllerShouldReturnItemNotFoundExceptionTemplateWhenNoCountriesHaveBeenFound() throws Exception {
        when(countryServiceMock.findAll()).thenThrow(new ItemNotFoundException(ExceptionMessages.NOT_FOUND_COUNTRY));

        assertThrows(ItemNotFoundException.class, () -> countryController.findAll());

        mockMvc.perform(get(Endpoints.REQUEST_MAPPING_COUNTRIES))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.NOT_FOUND_COUNTRY)))
            ;
    }

    @Test
    @DisplayName("Country controller should thrown internal server body")
    void countryControllerShouldReturnInternalServerError() throws Exception {
        when(countryServiceMock.findAll()).thenThrow(new TestException());

        mockMvc.perform(get(Endpoints.REQUEST_MAPPING_COUNTRIES))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.INTERNAL_SERVER_ERROR)));
    }
}
