package com.honeyautomation.apiplayground.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class LoginResponseDTO {

    private final String token;
}
