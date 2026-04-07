package com.huynguyenngocdang.utils;

import static com.huynguyenngocdang.constant.AppConstant.DATE_FORMAT;
import static com.huynguyenngocdang.constant.AppConstant.DATE_TIME_FORMAT;
import static com.huynguyenngocdang.constant.AppConstant.DEFAULT_TIME_ZONE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

    public static String getCurrentDate() {
        return LocalDate.now(DEFAULT_TIME_ZONE).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String getCurrentDateTime() {
        return LocalDateTime.now(DEFAULT_TIME_ZONE).format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }
}
