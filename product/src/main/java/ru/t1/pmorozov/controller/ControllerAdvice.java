package ru.t1.pmorozov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.t1.pmorozov.dto.ErrorRespDto;
import ru.t1.pmorozov.exceptions.PaymentRespException;
import ru.t1.pmorozov.exceptions.ProductRespException;
import ru.t1.pmorozov.exceptions.RepoException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RepoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorRespDto handleRepoException(RepoException repoException) {
        return new ErrorRespDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), repoException.getMessage());
    }

    @ExceptionHandler(ProductRespException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRespDto handleProductRespException(ProductRespException exception) {
        return new ErrorRespDto(exception.getHttpStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(PaymentRespException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRespDto handlePaymentRespException(PaymentRespException exception) {
        return new ErrorRespDto(exception.getHttpStatus().value(), exception.getMessage());
    }

}
