package com.huynguyenngocdang.constant;

import java.time.ZoneId;

public class AppConstant {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final ZoneId DEFAULT_TIME_ZONE = ZoneId.of("Asia/Ho_Chi_Minh");

    public static final String API_SUCCESS_CODE = "200";
    public static final String API_SUCCESS_MESSAGE = "Success";
    public static final String API_ERROR_CODE = "500";
    public static final String API_ERROR_MESSAGE = "Error";

    public static final String CLIENT_SIDE_ERROR_CODE = "400";
    public static final String VALIDATION_ERROR_MESSAGE = "Validation error";
    public static final String REQUEST_BODY_MISSING_ERROR_MESSAGE = "Request body is missing or malformed";
}
