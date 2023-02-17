package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.dto.response.CountryResponseDTO;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.repository.CountryRepository;
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
}
