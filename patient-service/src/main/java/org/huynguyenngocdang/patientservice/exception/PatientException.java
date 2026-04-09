package org.huynguyenngocdang.patientservice.exception;

import lombok.Getter;

@Getter
public class PatientException extends RuntimeException {
    private final String errorCode;
    private final Object payload;

    public PatientException(String code, String message) {
        super(message, null);
        this.errorCode = code;
        this.payload = null;
    }

    public PatientException(String code, String message, Object payload) {
        super(message, null);
        this.errorCode = code;
        this.payload = payload;
    }
}
