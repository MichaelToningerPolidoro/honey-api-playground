package com.honeyautomation.apiplayground.factory;

import com.honeyautomation.apiplayground.constants.Validations;
import com.honeyautomation.apiplayground.domain.Hobby;

public class HobbyFactory {

    private static int id = 1;

    private HobbyFactory() {}

    public static Hobby validHobby() {
        return new Hobby(getValidId(), "Programming" + System.currentTimeMillis());
    }

    public static Hobby hobbyWithNullHobbyValue() {
        return new Hobby(getValidId(), null);
    }

    public static Hobby hobbyTooBig() {
        return new Hobby(getValidId(), getHobbyValueTooBig());
    }

    private static int getValidId() {
        return id++;
    }

    private static String getHobbyValueTooBig() {
        final String character = "g";
        final int length = Validations.HOBBY_VALUE_MAX_LENGTH + 1;
        return character.repeat(length);
    }
}
