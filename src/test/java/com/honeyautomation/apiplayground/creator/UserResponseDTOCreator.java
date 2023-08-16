package com.honeyautomation.apiplayground.creator;

import com.honeyautomation.apiplayground.dto.response.UserResponseDTO;

public final class UserResponseDTOCreator {

    private UserResponseDTOCreator() {}

    public static UserResponseDTO validUserResponseDTO() {
        return new UserResponseDTO(UserCreator.validUser());
    }
}
