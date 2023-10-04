package com.platform.momentum.exception;

public class InvestorNotFoundException extends RuntimeException {

    public InvestorNotFoundException(String message) {
        super(message);
    }

    public InvestorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
