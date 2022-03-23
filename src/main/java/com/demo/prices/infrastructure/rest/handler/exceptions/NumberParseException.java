package com.demo.prices.infrastructure.rest.handler.exceptions;

public class NumberParseException extends Exception {

    public static final String MESSAGE = "Cannot convert the parameter entered as a string to number. Conflict parameters: productId or BrandId.";
}
