package com.honeyautomation.apiplayground.utils;

public final class ValuesGenerator {

    private static final String CHARACTER = "g";

    private ValuesGenerator() {}

    public static String getStringExceedingLengthLimit(int lengthLimit) {
        return CHARACTER.repeat(lengthLimit + 1);
    }
}
