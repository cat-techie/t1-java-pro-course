package ru.t1.pmorozov.exceptions;

import org.springframework.http.HttpStatus;

public class ProductRespException extends RuntimeException {
    private final HttpStatus httpStatus;

    public ProductRespException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
