package com.dummy.quickdirtyblog.utils;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.MILLIS;
import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class Utils {
  public static LocalDateTime now() {
    return LocalDateTime.now(UTC.normalized()).truncatedTo(MILLIS);
  }
}
