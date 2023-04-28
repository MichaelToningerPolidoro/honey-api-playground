package com.honeyautomation.apiplayground.creator;

import com.honeyautomation.apiplayground.dto.model.BornDataDTO;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;

import java.util.List;

public final class RegisterRequestDTOCreator {

    private RegisterRequestDTOCreator() {
    }

    public static RegisterRequestDTO validRegisterRequestDTO() {
        return RegisterRequestDTO.builder()
                .nickName(getValidNickName())
                .name(getValidName())
                .email(getValidEmail())
                .password(getValidPassword())
                .programmingTime(getValidProgrammingTimeOption())
                .bornData(getValidBornDateData())
                .hobbies(getValidHobbiesList())
                .build();
    }

    public static RegisterRequestDTO invalidRegisterRequestDTO() {
        return RegisterRequestDTO.builder()
                .nickName(null)
                .name(getValidName())
                .email(getValidEmail())
                .password(getValidPassword())
                .programmingTime(getValidProgrammingTimeOption())
                .bornData(getValidBornDateData())
                .hobbies(getValidHobbiesList())
                .build();
    }

    private static String getValidName() {
        return "Valid Name";
    }

    private static String getValidNickName() {
        return "ValidNickName";
    }

    private static String getValidEmail() {
        return "validemail456@test.com";
    }

    private static String getValidPassword() {
        return "validPassword";
    }

    private static String getValidProgrammingTimeOption() {
        return "Valid programming time";
    }

    private static BornDataDTO getValidBornDateData() {
        return new BornDataDTO("1970-01-01", "Valid Country");
    }

    private static List<String> getValidHobbiesList() {
        return List.of("valid hobby");
    }
}
