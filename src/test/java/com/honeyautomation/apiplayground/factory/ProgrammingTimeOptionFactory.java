package com.honeyautomation.apiplayground.factory;

import com.honeyautomation.apiplayground.domain.ProgrammingTimeOption;

public class ProgrammingTimeOptionFactory {

    public static ProgrammingTimeOption validProgrammingTimeOption() {
        return new ProgrammingTimeOption(1, "Test time option");
    }
}
