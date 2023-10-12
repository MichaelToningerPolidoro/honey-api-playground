package com.honey.apiplayground.repository;

import com.honey.apiplayground.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    Country findByCountry(String country);
}
