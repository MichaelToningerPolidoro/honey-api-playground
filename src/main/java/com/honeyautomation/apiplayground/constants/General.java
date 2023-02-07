package com.honeyautomation.apiplayground.constants;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class General {

    private General() {}

    public static final DateTimeFormatter STANDARD_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final ZoneId STANDARD_ZONE_ID = ZoneId.of("UTC");
}
