package com.honeyautomation.apiplayground.factory;

import com.honeyautomation.apiplayground.domain.BornData;
import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.domain.Password;
import com.honeyautomation.apiplayground.domain.User;

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
                .programmingTimeOption(ProgrammingTimeOptionCreator.validProgrammingTimeOption())
                .bornData(getValidBornData())
                .hobbies(getValidHobbiesList())
                .build();
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

    private static BornData getValidBornData() {
        return new BornData(LocalDate.now(), CountryCreator.validCountry());
    }

    private static List<Hobby> getValidHobbiesList() {
        return List.of(HobbyCreator.validHobby());
    }
}
