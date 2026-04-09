package org.huynguyenngocdang.patientservice.exception;

import com.huynguyenngocdang.common.ResponseApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdviceHandler {
    @ExceptionHandler(PatientException.class)
    public ResponseEntity<Object> handlePatientException(PatientException e) {
        log.error("PatientException: {}", e.getMessage(), e);
        if(e.getPayload() != null) {
            return new ResponseEntity<>(ResponseApi.error(e.getErrorCode(), e.getMessage(), e.getPayload()), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseApi.error(e.getErrorCode(), e.getMessage()), HttpStatus.OK);
    }
}
