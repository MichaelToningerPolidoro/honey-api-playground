package com.honey.apiplayground.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternValidationTest {

    @ParameterizedTest(name = "Value {0} should match date pattern: {1}")
    @MethodSource("datePatternTestProvidedParameters")
    void dateValidationPattern(String date, boolean expectedValue) {
        assertEquals(expectedValue, PatternValidation.isDateValid(date));
    }

    private static Arguments[] datePatternTestProvidedParameters() {
        return new Arguments[]{
                Arguments.of("1929-9-12", true),
                Arguments.of("1929-09-01", true),
                Arguments.of("2024-02-29", true),
                Arguments.of("9999-99-99", false),
                Arguments.of("1-1-1", false),
                Arguments.of("2000-6-", false),
                Arguments.of("2000-6-0", false),
                Arguments.of("0-04-08", false),
                Arguments.of("20000-0-6", false),
                Arguments.of("11-12-2012", false),
                Arguments.of("11-25-2023", false),
                Arguments.of("12/12/1999", false)
        };
    }
}
