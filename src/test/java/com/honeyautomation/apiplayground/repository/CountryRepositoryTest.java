package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.Country;
import com.honeyautomation.apiplayground.factory.CountryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    private Set<Country> countryDataSet;
    private final Country countryData = CountryFactory.validCountry();

    @BeforeEach
    public void before() {
        countryRepository.deleteAll();

        countryDataSet = new HashSet<>();
        countryDataSet.add(countryData);

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
        assertEquals(countryData.getCountry(), countriesRetrievedFromDatabase.get(0).getCountry());
        assertEquals(countryData.getIso(), countriesRetrievedFromDatabase.get(0).getIso());
    }
}
