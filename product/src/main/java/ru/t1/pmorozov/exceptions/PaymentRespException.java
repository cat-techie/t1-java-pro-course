package ru.t1.pmorozov.exceptions;

import org.springframework.http.HttpStatus;

public class PaymentRespException extends RuntimeException {
    private final HttpStatus httpStatus;

    public PaymentRespException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
