package com.honey.apiplayground.creator;

import com.honey.apiplayground.constants.Validations;
import com.honey.apiplayground.domain.Hobby;
import com.honey.apiplayground.utils.ValuesGenerator;

public final class HobbyCreator {

    private static int id = 1;

    private HobbyCreator() {}

    public static Hobby validHobby() {
        return new Hobby(getValidId(), "Programming" + System.currentTimeMillis());
    }

    public static Hobby getCopyWithDifferentId(Hobby hobby) {
        return new Hobby(getValidId(), hobby.getHobby());
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
