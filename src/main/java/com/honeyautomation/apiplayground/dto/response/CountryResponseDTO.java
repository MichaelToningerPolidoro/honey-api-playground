package com.honeyautomation.apiplayground.dto.response;

import com.honeyautomation.apiplayground.domain.Country;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CountryResponseDTO {

    private final List<String> countries;

    public CountryResponseDTO(List<Country> countries) {
        this.countries = countries.stream().map(Country::getCountry).collect(Collectors.toList());
    }
}
