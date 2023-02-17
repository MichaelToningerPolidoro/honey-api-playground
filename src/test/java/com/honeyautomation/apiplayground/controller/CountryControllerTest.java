package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.domain.Country;
import com.honeyautomation.apiplayground.dto.response.CountryResponseDTO;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.factory.CountryFactory;
import com.honeyautomation.apiplayground.factory.MockMvcFactory;
import com.honeyautomation.apiplayground.service.CountryService;
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
public class CountryControllerTest {

    @InjectMocks
    private CountryController countryController;

    @Mock
    private CountryService countryServiceMock;


    @Test
    @DisplayName("Country controller should return list of hobbies successfully")
    void countryControllerShouldReturnCountriesWhenSuccessfully() {
        final Country countryDataMock = CountryFactory.validCountry();
        final CountryResponseDTO responseDTOMockData = new CountryResponseDTO(List.of(countryDataMock));
        when(countryServiceMock.findAll()).thenReturn(responseDTOMockData);

        assertDoesNotThrow(() -> countryController.findAll(), ItemNotFoundException.class.getSimpleName());

        final CountryResponseDTO responseBody = countryController.findAll().getBody();

        assertNotNull(responseBody);
        assertNotNull(responseBody.getCountries());
        assertFalse(responseBody.getCountries().isEmpty());
        assertEquals(responseBody.getCountries().size(), responseDTOMockData.getCountries().size());
        assertTrue(responseBody.getCountries().contains(countryDataMock.getCountry()));
    }

    @Test
    @DisplayName("Country controller should return ItemNotFoundExceptionTemplate body")
    void countryControllerShouldReturnItemNotFoundExceptionTemplateWhenNoCountriesHaveBeenFound() throws Exception {
        final MockMvc mockMvc = MockMvcFactory.create(countryController);

        when(countryServiceMock.findAll()).thenThrow(new ItemNotFoundException(ExceptionMessages.NOT_FOUND_COUNTRY));

        assertThrows(ItemNotFoundException.class, () -> countryController.findAll());

        mockMvc.perform(get(Endpoints.REQUEST_MAPPING_COUNTRIES))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(ExceptionMessages.NOT_FOUND_COUNTRY)))
            ;
    }
}
