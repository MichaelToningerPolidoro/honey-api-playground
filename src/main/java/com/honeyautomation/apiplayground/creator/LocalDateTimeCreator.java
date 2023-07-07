package com.honeyautomation.apiplayground.creator;

import com.honeyautomation.apiplayground.constants.General;

import java.time.LocalDateTime;

public final class LocalDateTimeCreator {

    private LocalDateTimeCreator() {}

    public static LocalDateTime getToday() {
        return LocalDateTime.now(General.STANDARD_ZONE_ID);
    }
}
