package com.honeyautomation.apiplayground.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemNotFoundExceptionResponseTemplate {

    private String message;
}
