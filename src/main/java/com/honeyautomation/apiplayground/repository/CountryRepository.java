package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
