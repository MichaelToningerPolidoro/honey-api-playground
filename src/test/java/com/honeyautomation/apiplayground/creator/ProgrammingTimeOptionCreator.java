package com.honeyautomation.apiplayground.creator;

import com.honeyautomation.apiplayground.constants.Validations;
import com.honeyautomation.apiplayground.domain.ProgrammingTimeOption;
import com.honeyautomation.apiplayground.utils.ValuesGenerator;

public final class ProgrammingTimeOptionCreator {

    private static int id = 1;

    private ProgrammingTimeOptionCreator() {}

    public static ProgrammingTimeOption validProgrammingTimeOption() {
        return new ProgrammingTimeOption(getValidId(), getValidProgrammingTimeValue());
    }

    public static ProgrammingTimeOption getCopyWithDifferentId(ProgrammingTimeOption programmingTimeOption) {
        return new ProgrammingTimeOption(getValidId(), programmingTimeOption.getProgrammingTime());
    }

    public static ProgrammingTimeOption programmingTimeOptionWithNullProgrammingTimeOptionValue() {
        return new ProgrammingTimeOption(getValidId(), null);
    }

    public static ProgrammingTimeOption programmingTimeOptionWithProgrammingTimeOptionValueTooBig() {
        return new ProgrammingTimeOption(getValidId(), getProgrammingTimeValueTooBig());
    }

    private static int getValidId() {
        return id++;
    }

    private static String getValidProgrammingTimeValue() {
        return "programming time option" + System.currentTimeMillis();
    }

    private static String getProgrammingTimeValueTooBig() {
        return ValuesGenerator.getStringExceedingLengthLimit(Validations.MAX_LENGTH_PROGRAMMING_TIME);
    }
}
