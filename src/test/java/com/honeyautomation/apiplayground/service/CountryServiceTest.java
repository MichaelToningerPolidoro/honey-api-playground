package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.domain.Country;
import com.honeyautomation.apiplayground.dto.response.CountryResponseDTO;
import com.honeyautomation.apiplayground.exception.ItemNotFoundException;
import com.honeyautomation.apiplayground.factory.CountryFactory;
import com.honeyautomation.apiplayground.repository.CountryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CountryServiceTest {

    @InjectMocks
    private CountryService countryService;

    @Mock
    private CountryRepository countryRepositoryMock;

    @Test
    @DisplayName("Find all countries should return a list successfully")
    void findAllCountriesShouldReturnAList() {
        final Country countryMockData = CountryFactory.validCountry();
        final List<Country> countryListMockData = List.of(countryMockData);
        when(countryRepositoryMock.findAll()).thenReturn(countryListMockData);

        assertDoesNotThrow(() -> countryService.findAll(), ItemNotFoundException.class.getSimpleName());

        final CountryResponseDTO allCountriesFound = countryService.findAll();

        assertNotNull(allCountriesFound);
        assertNotNull(allCountriesFound.getCountries());
        assertFalse(allCountriesFound.getCountries().isEmpty());
        assertEquals(allCountriesFound.getCountries().size(), countryListMockData.size());
        assertTrue(allCountriesFound.getCountries().contains(countryMockData.getCountry()));
    }

    @Test
    @DisplayName("find all should throw ItemNotFoundException when no countries have been found")
    void findAllCountriesShouldThrowItemNotFoundException() {
        when(countryRepositoryMock.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ItemNotFoundException.class, () -> countryService.findAll());
    }
}
