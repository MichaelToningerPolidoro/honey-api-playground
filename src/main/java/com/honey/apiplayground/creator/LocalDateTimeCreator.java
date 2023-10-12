package com.honey.apiplayground.creator;

import com.honey.apiplayground.constants.General;

import java.time.LocalDateTime;

public final class LocalDateTimeCreator {

    private LocalDateTimeCreator() {}

    public static LocalDateTime getToday() {
        return LocalDateTime.now(General.STANDARD_ZONE_ID);
    }
}
