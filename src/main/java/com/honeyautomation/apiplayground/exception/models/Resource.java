package com.honeyautomation.apiplayground.exception.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Resource {

    private final String method;
    private final String endpoint;
}