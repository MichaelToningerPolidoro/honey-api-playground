package com.honey.apiplayground.dto.response;

import com.honey.apiplayground.domain.ProgrammingTimeOption;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class ProgrammingTimeOptionResponseDTO implements Serializable {

    private final List<String> programmingTimeOptions;

    public ProgrammingTimeOptionResponseDTO(List<ProgrammingTimeOption> programmingTimeOptions) {
        this.programmingTimeOptions = programmingTimeOptions.stream()
                .map(ProgrammingTimeOption::getProgrammingTime).collect(Collectors.toList());
    }
}
