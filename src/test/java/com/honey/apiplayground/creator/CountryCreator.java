package com.honey.apiplayground.creator;

import com.honey.apiplayground.constants.Validations;
import com.honey.apiplayground.domain.Country;
import com.honey.apiplayground.utils.ValuesGenerator;

public final class CountryCreator {

    private static int id = 1;
    private static int isoCount = 1;

    private CountryCreator() {}

    public static Country validCountry() {
        return new Country(getValidId(), getValidCountryName(), getValidIso());
    }

    public static Country countryWithNullCountryValue() {
        return new Country(getValidId(), null, getValidIso());
    }

    public static Country countryWithNullIsoValue() {
        return new Country(getValidId(), getValidCountryName(), null);
    }

    public static Country countryWithCountryValueTooBig() {
        return new Country(getValidId(), getCountryNameTooBig(), getValidIso());
    }

    public static Country countryWithIsoValueTooBig() {
        return new Country(getValidId(), getValidCountryName(), getIsoTooBig());
    }

    private static int getValidId() {
        return id++;
    }

    private static int getValidIsoCount() {
        return isoCount++;
    }

    private static String getValidIso() {
        return String.valueOf(getValidIsoCount());
    }

    private static String getValidCountryName() {
        return "Country" + System.currentTimeMillis();
    }

    private static String getCountryNameTooBig() {
        return ValuesGenerator.getStringExceedingLengthLimit(Validations.MAX_LENGTH_COUNTRY);
    }

    private static String getIsoTooBig() {
        return ValuesGenerator.getStringExceedingLengthLimit(Validations.MAX_LENGTH_COUNTRY_ISO);
    }

    public static Country getCopyWithDifferentId(Country country) {
        return new Country(getValidId(), country.getCountry(), country.getIso());
    }
}
