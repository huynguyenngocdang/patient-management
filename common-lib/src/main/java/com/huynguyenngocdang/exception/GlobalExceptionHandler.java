package com.huynguyenngocdang.exception;

import lombok.extern.slf4j.Slf4j;
import com.huynguyenngocdang.common.ResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.huynguyenngocdang.constant.AppConstant.CLIENT_SIDE_ERROR_CODE;
import static com.huynguyenngocdang.constant.AppConstant.VALIDATION_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseApi<Object>> handleException(Exception e) {
        log.error("Exception: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseApi.error(null, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseApi<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage(), e);
        String errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> {
                    String errorMsg = fieldError.getDefaultMessage() != null
                            ? fieldError.getDefaultMessage()
                            : fieldError.getRejectedValue() + " is invalid";
                    return fieldError.getField() + ": " + errorMsg;
                })
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseApi.error(CLIENT_SIDE_ERROR_CODE, errors));
    }
}
