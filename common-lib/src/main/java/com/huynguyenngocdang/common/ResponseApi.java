package com.huynguyenngocdang.common;

import static com.huynguyenngocdang.constant.AppConstant.API_ERROR_CODE;
import static com.huynguyenngocdang.constant.AppConstant.API_ERROR_MESSAGE;
import static com.huynguyenngocdang.constant.AppConstant.API_SUCCESS_CODE;
import static com.huynguyenngocdang.constant.AppConstant.API_SUCCESS_MESSAGE;

import org.springframework.util.StringUtils;

public record ResponseApi<T>(ResponseStatus status, T data, ResponseMetadata metadata) {

    public static <T> ResponseApi<T> success(T data) {
        ResponseStatus status = new ResponseStatus(API_SUCCESS_CODE, API_SUCCESS_MESSAGE);
        ResponseMetadata metaData = ResponseMetadata.getCurrentMetadata();
        return new ResponseApi<>(status, data, metaData);
    }

    public static <T> ResponseApi<T> error(String errorCode, String errorMessage) {
        String errCode = StringUtils.hasText(errorCode) ? errorCode : API_ERROR_CODE;
        String errMessage = StringUtils.hasText(errorMessage) ? errorMessage : API_ERROR_MESSAGE;
        ResponseStatus status = new ResponseStatus(errCode, errMessage);
        ResponseMetadata metaData = ResponseMetadata.getCurrentMetadata();
        return new ResponseApi<>(status, null, metaData);
    }

    public static <T> ResponseApi<T> error(String errorCode, String errorMessage, T data) {
        String errCode = StringUtils.hasText(errorCode) ? errorCode : API_ERROR_CODE;
        String errMessage = StringUtils.hasText(errorMessage) ? errorMessage : API_ERROR_MESSAGE;
        ResponseStatus status = new ResponseStatus(errCode, errMessage);
        ResponseMetadata metaData = ResponseMetadata.getCurrentMetadata();
        return new ResponseApi<>(status, data, metaData);
    }
}
