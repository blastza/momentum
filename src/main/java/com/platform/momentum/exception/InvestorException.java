package com.platform.momentum.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class InvestorException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public InvestorException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }
}
