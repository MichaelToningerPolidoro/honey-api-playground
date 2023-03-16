package com.honeyautomation.apiplayground.factory;

import com.honeyautomation.apiplayground.constants.Validations;
import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.utils.ValuesGenerator;

public final class HobbyFactory {

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
        return ValuesGenerator.getStringExceedingLengthLimit(Validations.MAX_LENGTH_HOBBY_VALUE);
    }
}
