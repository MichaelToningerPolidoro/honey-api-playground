package com.honey.apiplayground.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public final class LoginResponseDTO implements Serializable {

    private final String token;
}
