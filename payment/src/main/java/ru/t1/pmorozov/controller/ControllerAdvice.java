package ru.t1.pmorozov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.t1.pmorozov.dto.ErrorRespDto;
import ru.t1.pmorozov.exceptions.IntegrationException;
import ru.t1.pmorozov.exceptions.PaymentException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(PaymentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorRespDto handlePaymentException(PaymentException exception) {
        return new ErrorRespDto(exception.getHttpStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(IntegrationException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorRespDto handleIntegrationException(IntegrationException exception) {
        return new ErrorRespDto(exception.getHttpStatus().value(), exception.getMessage());
    }
}
