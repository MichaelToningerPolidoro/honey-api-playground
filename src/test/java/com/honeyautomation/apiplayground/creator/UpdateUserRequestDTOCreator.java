package com.honeyautomation.apiplayground.creator;

import com.honeyautomation.apiplayground.dto.model.BornDataDTO;
import com.honeyautomation.apiplayground.dto.request.UpdateUserRequestDTO;

import java.util.List;

import static com.honeyautomation.apiplayground.creator.CountryCreator.validCountry;
import static com.honeyautomation.apiplayground.creator.HobbyCreator.validHobby;
import static com.honeyautomation.apiplayground.creator.ProgrammingTimeOptionCreator.validProgrammingTimeOption;

public final class UpdateUserRequestDTOCreator {

    private UpdateUserRequestDTOCreator() {}

    public static UpdateUserRequestDTO validUpdateUserRequest() {
        return UpdateUserRequestDTO.builder()
                .nickName(getValidNickName())
                .name(getValidName())
                .password(getValidPassword())
                .programmingTime(getValidProgrammingTimeOption())
                .hobbies(getValidHobbiesList())
                .bornData(getValidBornDateData())
                .build();
    }

    private static String getValidNickName() {
        return "ValidNickName" + System.currentTimeMillis();
    }

    private static String getValidName() {
        return "Valid Name";
    }

    private static String getValidPassword() {
        return "ValidPassword";
    }

    private static String getValidProgrammingTimeOption() {
        return validProgrammingTimeOption().getProgrammingTime();
    }

    private static List<String> getValidHobbiesList() {
        return List.of(validHobby().getHobby());
    }

    private static BornDataDTO getValidBornDateData() {
        return new BornDataDTO("1970-01-01", validCountry().getCountry());
    }
}
