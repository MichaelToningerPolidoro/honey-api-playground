package com.honeyautomation.apiplayground.factory;

import com.honeyautomation.apiplayground.constants.Validations;
import com.honeyautomation.apiplayground.domain.*;
import com.honeyautomation.apiplayground.utils.ValuesGenerator;

import java.time.LocalDate;
import java.util.List;

public final class UserCreator {

    private static final ProgrammingTimeOption validProgrammingTimeOptionCache = getValidProgrammingTimeOption();
    private static final Country validCountryCache = CountryCreator.validCountry();
    private static final List<Hobby> hobbiesListCache = getValidHobbiesList();

    private UserCreator() {}

    public static User validUser() {
        return User.builder()
                .nickName(getValidNickName())
                .name(getValidName())
                .email(getValidEmail())
                .password(getValidPassword())
                .programmingTimeOption(validProgrammingTimeOptionCache)
                .bornData(getValidBornData())
                .hobbies(hobbiesListCache)
                .build();
    }

    public static User userWithNullNickNameValue() {
        return User.builder()
                .nickName(null)
                .name(getValidName())
                .email(getValidEmail())
                .password(getValidPassword())
                .programmingTimeOption(validProgrammingTimeOptionCache)
                .bornData(getValidBornData())
                .hobbies(hobbiesListCache)
                .build();
    }

    public static User userWithNickNameTooBig() {
        return User.builder()
                .nickName(getNickNameTooBig())
                .name(getValidName())
                .email(getValidEmail())
                .password(getValidPassword())
                .programmingTimeOption(validProgrammingTimeOptionCache)
                .bornData(getValidBornData())
                .hobbies(hobbiesListCache)
                .build();
    }

    private static String getNickNameTooBig() {
        return ValuesGenerator.getStringExceedingLengthLimit(Validations.MAX_LENGTH_NICK_NAME);
    }

    private static String getValidNickName() {
        return "ValidNick" + System.currentTimeMillis();
    }

    private static String getValidName() {
        return "Valid Name " + System.currentTimeMillis();
    }

    private static String getValidEmail() {
        return "email" + System.currentTimeMillis() + "@testing.com";
    }

    private static Password getValidPassword() {
        return new Password("ValidPassword");
    }

    private static ProgrammingTimeOption getValidProgrammingTimeOption() {
        return ProgrammingTimeOptionCreator.validProgrammingTimeOption();
    }

    private static BornData getValidBornData() {
        return new BornData(LocalDate.now(), validCountryCache);
    }

    private static List<Hobby> getValidHobbiesList() {
        return List.of(HobbyCreator.validHobby());
    }
}
