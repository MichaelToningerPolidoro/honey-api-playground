package com.honey.apiplayground.creator;

import com.honey.apiplayground.dto.response.UserResponseDTO;

public final class UserResponseDTOCreator {

    private UserResponseDTOCreator() {}

    public static UserResponseDTO validUserResponseDTO() {
        return new UserResponseDTO(UserCreator.validUser());
    }
}
