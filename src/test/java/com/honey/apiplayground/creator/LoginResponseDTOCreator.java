package com.honey.apiplayground.creator;

import com.honey.apiplayground.dto.response.LoginResponseDTO;

public final class LoginResponseDTOCreator {

    private LoginResponseDTOCreator() {}

    public static LoginResponseDTO validLoginResponseDTO() {
        return new LoginResponseDTO("Some valid login token");
    }
}
