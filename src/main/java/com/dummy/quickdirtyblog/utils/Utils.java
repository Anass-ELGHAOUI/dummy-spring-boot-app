package com.dummy.quickdirtyblog.utils;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.MILLIS;

import java.time.LocalDateTime;

public class Utils {

    public static LocalDateTime now() {
        return LocalDateTime.now(UTC.normalized()).truncatedTo(MILLIS);
    }
}
