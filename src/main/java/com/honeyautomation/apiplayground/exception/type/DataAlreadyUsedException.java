package com.honeyautomation.apiplayground.exception.type;

import com.honeyautomation.apiplayground.exception.models.DataAlreadyUsedInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class DataAlreadyUsedException extends RuntimeException {

    private final List<DataAlreadyUsedInfo> dataAlreadyUsed;

    public DataAlreadyUsedException(List<DataAlreadyUsedInfo> dataAlreadyUsed) {
        this.dataAlreadyUsed = dataAlreadyUsed;
    }
}
