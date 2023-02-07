package com.honeyautomation.apiplayground.utils;

import com.honeyautomation.apiplayground.constants.Validations;

import java.util.regex.Pattern;

public final class PatternValidationUtils {

    private PatternValidationUtils() {}

    public static boolean isDateValid(String rawDate) {
        return Pattern.matches(Validations.DATE_VALIDATION_REGEX_PATTERN, rawDate);
    }
}
