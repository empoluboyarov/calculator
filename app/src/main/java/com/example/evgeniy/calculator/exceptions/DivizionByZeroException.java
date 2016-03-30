package com.example.evgeniy.calculator.exceptions;

/**
 * Created by Evgeniy on 30.03.2016.
 */
public class DivizionByZeroException extends ArithmeticException {

    public static final long serialVersonUID = 1L;

    public DivizionByZeroException() {
    }

    public DivizionByZeroException(String message) {
        super(message);
    }
}
