package com.honey.apiplayground.exception.type;

import lombok.Getter;

import java.util.List;

@Getter
public class DataAlreadyUsedException extends RuntimeException {

    private final List<String> dataAlreadyUsed;

    public DataAlreadyUsedException(List<String> dataAlreadyUsed) {
        this.dataAlreadyUsed = dataAlreadyUsed;
    }
}
