package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.Country;
import com.honeyautomation.apiplayground.factory.CountryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    private Set<Country> countryDataSet;
    private static final Country countryDataToSaveBeforeTests = CountryFactory.validCountry();

    @BeforeEach
    public void before() {
        countryRepository.deleteAll();

        countryDataSet = new HashSet<>();
        countryDataSet.add(countryDataToSaveBeforeTests);

        countryRepository.saveAll(countryDataSet);
    }

    @Test
    @DisplayName("Retrieve all values from database should return all countries successfully")
    void findAllShouldReturnAllCountriesSuccessfully() {
        final List<Country> countriesRetrievedFromDatabase = countryRepository.findAll();

        assertNotNull(countriesRetrievedFromDatabase);
        assertFalse(countriesRetrievedFromDatabase.isEmpty());
        assertEquals(countryDataSet.size(), countriesRetrievedFromDatabase.size());
        assertNotNull(countriesRetrievedFromDatabase.get(0));
        assertInstanceOf(Integer.class, countriesRetrievedFromDatabase.get(0).getId());
        assertEquals(countryDataToSaveBeforeTests.getCountry(), countriesRetrievedFromDatabase.get(0).getCountry());
        assertEquals(countryDataToSaveBeforeTests.getIso(), countriesRetrievedFromDatabase.get(0).getIso());
    }

    @Test
    @DisplayName("Saving new country should be successfully inserted")
    void savingNewValidCountryShouldBeSuccessfullyInserted() {
        final Country countryToSave = CountryFactory.validCountry();
        final Country savedCountry = countryRepository.save(countryToSave);

        assertNotNull(savedCountry);
        assertInstanceOf(Integer.class, savedCountry.getId());
        assertEquals(countryToSave.getCountry(), savedCountry.getCountry());
        assertEquals(countryToSave.getIso(), savedCountry.getIso());
    }

    @ParameterizedTest(name = "Saving new country should thrown constraint violation")
    @MethodSource("constraintViolationProvidedParameters")
    void savingNewCountryShouldThrowConstraintViolationException(Country country) {
        assertThrows(DataIntegrityViolationException.class, () -> countryRepository.save(country));
    }

    private static Arguments[] constraintViolationProvidedParameters() {
        return new Arguments[] {
                Arguments.of(countryDataToSaveBeforeTests),
                Arguments.of(CountryFactory.countryWithNullCountryValue()),
                Arguments.of(CountryFactory.countryWithNullIsoValue()),
                Arguments.of(CountryFactory.countryWithCountryValueTooBig()),
                Arguments.of(CountryFactory.countryWithIsoValueTooBig())
        };
    }
}
