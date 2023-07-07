package com.honeyautomation.apiplayground.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponseDTO {

    private final String token;
}
