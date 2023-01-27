package com.honeyautomation.apiplayground.factory;

import com.honeyautomation.apiplayground.domain.Country;

public class CountryFactory {

    private CountryFactory() {}

    public static Country validCountry() {
        return new Country(1, "Some Country", "ISO");
    }
}
