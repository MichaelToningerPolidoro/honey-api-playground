package com.honeyautomation.apiplayground.exception.models;

import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DataAlreadyUsedInfo {

    private final String field;
    private final String message;

    public DataAlreadyUsedInfo(String field) {
        this.field = field;
        this.message = ExceptionMessages.DATA_ALREADY_USED;
    }
}
