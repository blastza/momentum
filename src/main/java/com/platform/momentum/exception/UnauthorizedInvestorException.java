package com.platform.momentum.exception;

public class UnauthorizedInvestorException extends RuntimeException{
    public UnauthorizedInvestorException(String message) {
        super(message);
    }

    public UnauthorizedInvestorException(String message, Throwable cause) {
        super(message, cause);
    }
}
