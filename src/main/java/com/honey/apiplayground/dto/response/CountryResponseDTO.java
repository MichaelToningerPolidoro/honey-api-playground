package com.honey.apiplayground.dto.response;

import com.honey.apiplayground.domain.Country;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class CountryResponseDTO implements Serializable {

    private final List<String> countries;

    public CountryResponseDTO(List<Country> countries) {
        this.countries = countries.stream().map(Country::getCountry).collect(Collectors.toList());
    }
}
