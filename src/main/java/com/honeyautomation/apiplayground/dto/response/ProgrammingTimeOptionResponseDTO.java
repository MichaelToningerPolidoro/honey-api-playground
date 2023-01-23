package com.honeyautomation.apiplayground.dto.response;

import com.honeyautomation.apiplayground.domain.ProgrammingTimeOption;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProgrammingTimeOptionResponseDTO {

    private final List<String> programmingTimeOptions;

    public ProgrammingTimeOptionResponseDTO(List<ProgrammingTimeOption> programmingTimeOptions) {
        this.programmingTimeOptions = programmingTimeOptions.stream()
                .map(ProgrammingTimeOption::getProgrammingTime).collect(Collectors.toList());
    }
}
