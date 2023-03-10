package com.happy.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private final static String DATE_PATTERN_1 = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime dateStringToLocalDateTime(String dateStr, @Nullable String datePatternString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                StringUtils.hasText(datePatternString) ? datePatternString : DATE_PATTERN_1);
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static Long getCurrentDateTimestamp(){
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
