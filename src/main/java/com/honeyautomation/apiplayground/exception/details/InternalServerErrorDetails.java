package com.honeyautomation.apiplayground.exception.details;

import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
public class InternalServerErrorDetails implements Serializable {

    private final String message = ExceptionMessages.INTERNAL_SERVER_ERROR;
}
