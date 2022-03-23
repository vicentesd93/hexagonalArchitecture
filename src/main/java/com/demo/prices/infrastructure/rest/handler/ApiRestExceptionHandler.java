package com.demo.prices.infrastructure.rest.handler;

import com.demo.prices.infrastructure.rest.handler.exceptions.InvalidDateException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NotFoundPriceException;
import com.demo.prices.infrastructure.rest.handler.exceptions.NumberParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ApiRestExceptionHandler {

    @ExceptionHandler(NotFoundPriceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage notFoundPriceException() {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                NotFoundPriceException.MESSAGE);
    }

    @ExceptionHandler(InvalidDateException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage invalidDateException() {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                InvalidDateException.MESSAGE);
    }

    @ExceptionHandler(NumberParseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage numberParseException() {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                NumberParseException.MESSAGE);
    }
}
