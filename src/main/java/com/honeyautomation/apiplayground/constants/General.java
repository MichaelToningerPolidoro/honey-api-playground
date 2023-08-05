package com.honeyautomation.apiplayground.constants;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class General {

    private General() {}

    public static final DateTimeFormatter STANDARD_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final ZoneId STANDARD_ZONE_ID = ZoneId.of("UTC");
    public static final ZoneOffset STANDARD_ZONE_OFFSET = ZoneOffset.of("+00:00");

    public static final String MASK = "*****";
    public static final int MASK_MINIMUM_REPLACE_SIZE = 3;
}
