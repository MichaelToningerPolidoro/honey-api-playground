package com.honeyautomation.apiplayground.exception.details;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class DataAlreadyUsedExceptionDetails implements Serializable {

    private String field;
    private String message;
}
