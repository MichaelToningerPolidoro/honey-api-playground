package com.honey.apiplayground.utils;

import com.honey.apiplayground.constants.Validations;

import java.util.regex.Pattern;

public final class PatternValidation {

    private PatternValidation() {}

    public static boolean isDateValid(String rawDate) {
        return Pattern.matches(Validations.REGEX_PATTERN_DATE_VALIDATION, rawDate);
    }
}
