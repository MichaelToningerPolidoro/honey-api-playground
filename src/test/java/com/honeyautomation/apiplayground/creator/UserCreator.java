package com.honeyautomation.apiplayground.creator;

import com.honeyautomation.apiplayground.constants.Validations;
import com.honeyautomation.apiplayground.domain.*;
import com.honeyautomation.apiplayground.utils.ValuesGenerator;

import java.time.LocalDate;
import java.util.List;

public final class UserCreator {

    private UserCreator() {}

    public static User validUser() {
        return User.builder()
                .nickName(getValidNickName())
                .name(getValidName())
                .email(getValidEmail())
                .password(getValidPassword())
                .programmingTimeOption(getValidProgrammingTimeOption())
                .bornData(getValidBornData())
                .hobbies(getValidHobbiesList())
                .build();
    }

    public static User userWithNullNickNameValue() {
        return User.builder()
                .nickName(null)
                .name(getValidName())
                .email(getValidEmail())
                .password(getValidPassword())
                .programmingTimeOption(getValidProgrammingTimeOption())
                .bornData(getValidBornData())
                .hobbies(getValidHobbiesList())
                .build();
    }

    public static User userWithNickNameTooBig() {
        return User.builder()
                .nickName(getNickNameTooBig())
                .name(getValidName())
                .email(getValidEmail())
                .password(getValidPassword())
                .programmingTimeOption(getValidProgrammingTimeOption())
                .bornData(getValidBornData())
                .hobbies(getValidHobbiesList())
                .build();
    }

    public static User userCopyWithForeignValues(User user, ProgrammingTimeOption pto, Country country, List<Hobby> hobbies) {
        return User.builder()
                .nickName(user.getNickName())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .programmingTimeOption(pto)
                .bornData(getBornDataWithCountry(country))
                .hobbies(hobbies)
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
        return new BornData(LocalDate.now().minusYears(10), CountryCreator.validCountry());
    }

    private static BornData getBornDataWithCountry(Country country) {
        return new BornData(LocalDate.now().minusYears(10), country);
    }

    private static List<Hobby> getValidHobbiesList() {
        return List.of(HobbyCreator.validHobby());
    }
}
