package com.honeyautomation.apiplayground.utils;

import com.honeyautomation.apiplayground.constants.General;

public final class Mask {

    private Mask() {}

    public static String getMaskedEmail(String rawEmail) {
        final String AT = "@";
        final String[] splitEmail = rawEmail.split(AT);
        final String prefix = splitEmail[0];
        final String postfix = splitEmail[1];

        return getMasked(prefix) + AT + postfix;
    }

    private static String getMasked(String value) {
        if (value.length() >= General.MASK_MINIMUM_REPLACE_SIZE) {
            return value.substring(0, General.MASK_MINIMUM_REPLACE_SIZE) + General.MASK;
        }

        return value + General.MASK;
    }
}
