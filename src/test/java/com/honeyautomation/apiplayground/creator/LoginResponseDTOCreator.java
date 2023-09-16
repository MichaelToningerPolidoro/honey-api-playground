package com.honeyautomation.apiplayground.creator;

import com.honeyautomation.apiplayground.dto.response.LoginResponseDTO;

public final class LoginResponseDTOCreator {

    private LoginResponseDTOCreator() {}

    public static LoginResponseDTO validLoginResponseDTO() {
        return new LoginResponseDTO("Some valid login token");
    }
}
