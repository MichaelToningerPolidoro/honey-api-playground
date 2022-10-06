package com.honeyautomation.apiplayground.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemNotFoundExceptionResponseTemplate {

    private String message;
}
