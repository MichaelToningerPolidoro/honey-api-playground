package com.honeyautomation.apiplayground.exception.details;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
public class OnlyMessageDetails implements Serializable {

    private final String message;
}
