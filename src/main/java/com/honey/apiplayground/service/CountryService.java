package com.honey.apiplayground.service;

import com.honey.apiplayground.constants.Endpoints;
import com.honey.apiplayground.constants.ExceptionMessages;
import com.honey.apiplayground.domain.Country;
import com.honey.apiplayground.dto.response.CountryResponseDTO;
import com.honey.apiplayground.exception.models.Resource;
import com.honey.apiplayground.exception.type.ItemNotFoundException;
import com.honey.apiplayground.exception.type.ItemNotRegisteredException;
import com.honey.apiplayground.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional(readOnly = true)
    public CountryResponseDTO findAll() {
        final CountryResponseDTO countryResponseDTO = new CountryResponseDTO(countryRepository.findAll());

        if (countryResponseDTO.getCountries().isEmpty()) {
            throw new ItemNotFoundException(ExceptionMessages.NOT_FOUND_COUNTRY);
        }

        return countryResponseDTO;
    }

    @Transactional(readOnly = true)
    public Country findCountry(String country) {
        final Country countryFound = countryRepository.findByCountry(country);

        if (countryFound == null) {
            final Resource resource = new Resource(Endpoints.METHOD_FIND_ALL_COUNTRIES, Endpoints.REQUEST_MAPPING_COUNTRIES);
            throw new ItemNotRegisteredException(ExceptionMessages.NOT_REGISTERED_COUNTRY, resource);
        }

        return countryFound;
    }
}
