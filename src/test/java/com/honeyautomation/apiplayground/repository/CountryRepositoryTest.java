package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.creator.CountryCreator;
import com.honeyautomation.apiplayground.domain.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @BeforeAll
    static void beforeAll(@Autowired CountryRepository countryRepository) {
        countryRepository.deleteAll();
    }

    @Test
    @DisplayName("Retrieve all values from database should return all countries successfully")
    void findAllShouldReturnAllCountriesSuccessfully() {
        final Country countryToSave = CountryCreator.validCountry();
        countryRepository.save(countryToSave);

        final List<Country> countriesRetrievedFromDatabase = countryRepository.findAll();

        assertNotNull(countriesRetrievedFromDatabase);
        assertFalse(countriesRetrievedFromDatabase.isEmpty());
        assertNotNull(countriesRetrievedFromDatabase.get(0));
        assertInstanceOf(Integer.class, countriesRetrievedFromDatabase.get(0).getId());
        assertEquals(countryToSave.getCountry(), countriesRetrievedFromDatabase.get(0).getCountry());
        assertEquals(countryToSave.getIso(), countriesRetrievedFromDatabase.get(0).getIso());
    }

    @Test
    @DisplayName("Saving new country should be successfully inserted")
    void savingNewValidCountryShouldBeSuccessfullyInserted() {
        final Country countryToSave = CountryCreator.validCountry();
        final Country savedCountry = countryRepository.save(countryToSave);

        assertNotNull(savedCountry);
        assertInstanceOf(Integer.class, savedCountry.getId());
        assertEquals(countryToSave.getCountry(), savedCountry.getCountry());
        assertEquals(countryToSave.getIso(), savedCountry.getIso());
    }

    @Test
    @DisplayName("Saving duplicated country should thrown an exception")
    void savingDuplicatedCountryShouldThrownException() {
        final Country countryToSave = CountryCreator.validCountry();
        final Country duplicatedCountry = CountryCreator.getCopyWithDifferentId(countryToSave);

        countryRepository.save(countryToSave);

        assertThrows(DataIntegrityViolationException.class, () -> countryRepository.save(duplicatedCountry));
    }

    @ParameterizedTest(name = "Constraint violation tests")
    @MethodSource("constraintViolationProvidedParameters")
    void savingNewCountryShouldThrowConstraintViolationException(Country country) {
        assertThrows(DataIntegrityViolationException.class, () -> countryRepository.save(country));
    }

    private static Arguments[] constraintViolationProvidedParameters() {
        return new Arguments[] {
                Arguments.of(CountryCreator.countryWithNullCountryValue()),
                Arguments.of(CountryCreator.countryWithNullIsoValue()),
                Arguments.of(CountryCreator.countryWithCountryValueTooBig()),
                Arguments.of(CountryCreator.countryWithIsoValueTooBig())
        };
    }
}
