package ru.t1.pmorozov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.t1.pmorozov.dto.ErrorRespDto;
import ru.t1.pmorozov.exceptions.LimitRespException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(LimitRespException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRespDto handleProductRespException(LimitRespException exception) {
        return new ErrorRespDto(exception.getHttpStatus().value(), exception.getMessage());
    }
}
