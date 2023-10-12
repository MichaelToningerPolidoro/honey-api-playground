package com.honey.apiplayground.exception.details;

import com.honey.apiplayground.exception.models.Resource;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ItemNotRegisteredExceptionDetails implements Serializable {

    private String message;
    private Resource resource;
}
