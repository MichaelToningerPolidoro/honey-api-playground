package com.honeyautomation.apiplayground.creator;

import com.honeyautomation.apiplayground.constants.General;

import java.time.LocalDate;

public final class LocalDateCreator {

    private LocalDateCreator() {}

    public static LocalDate getLocalDate(String rawDate) {
        return LocalDate.parse(rawDate, General.STANDARD_DATE_TIME_FORMATTER)
                .atStartOfDay(General.STANDARD_ZONE_ID)
                .toLocalDate();
    }

    public static LocalDate getToday() {
        return LocalDate.now(General.STANDARD_ZONE_ID);
    }
}
