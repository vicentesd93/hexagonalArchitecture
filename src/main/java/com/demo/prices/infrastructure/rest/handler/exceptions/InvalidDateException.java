package com.demo.prices.infrastructure.rest.handler.exceptions;

import com.demo.prices.domain.vo.DatePrice;

public class InvalidDateException extends Exception {

    public static final String MESSAGE = "Date entered invalid. Date format (" + DatePrice.DATE_FORMAT + ").";
}
