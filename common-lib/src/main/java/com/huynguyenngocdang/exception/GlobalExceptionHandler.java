package com.huynguyenngocdang.exception;

import lombok.extern.slf4j.Slf4j;
import com.huynguyenngocdang.common.ResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.huynguyenngocdang.constant.AppConstant.CLIENT_SIDE_ERROR_CODE;
import static com.huynguyenngocdang.constant.AppConstant.REQUEST_BODY_MISSING_ERROR_MESSAGE;
import static com.huynguyenngocdang.constant.AppConstant.VALIDATION_ERROR_MESSAGE;

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
        Map<String, String> errors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> Optional.ofNullable(fieldError.getDefaultMessage()).orElse(VALIDATION_ERROR_MESSAGE),
                        (existing, duplicate) -> existing
                ));


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseApi.error(CLIENT_SIDE_ERROR_CODE, VALIDATION_ERROR_MESSAGE, errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseApi<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseApi.error(CLIENT_SIDE_ERROR_CODE, REQUEST_BODY_MISSING_ERROR_MESSAGE));
    }
}
