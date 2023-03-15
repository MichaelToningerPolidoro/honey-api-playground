package com.honeyautomation.apiplayground.factory;

import com.honeyautomation.apiplayground.constants.Validations;
import com.honeyautomation.apiplayground.domain.Country;
import com.honeyautomation.apiplayground.utils.ValuesGenerator;

public class CountryFactory {

    private static int id = 1;
    private static int isoCount = 1;

    private CountryFactory() {}

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
}
