package com.platform.momentum.exception.handler;

import com.platform.momentum.exception.InvestorException;
import com.platform.momentum.exception.InvestorNotFoundException;
import com.platform.momentum.exception.UnauthorizedInvestorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvestorExceptionHandler {

    @ExceptionHandler(value = {InvestorNotFoundException.class})
    public ResponseEntity<Object> handleInvestorNotFoundException
            (InvestorNotFoundException exception){
        InvestorException investorException = new InvestorException(
                exception.getMessage(),
                exception.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(investorException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UnauthorizedInvestorException.class})
    public ResponseEntity<Object> handleInvestorNotFoundException
            (UnauthorizedInvestorException exception){
        InvestorException investorException = new InvestorException(
                exception.getMessage(),
                exception.getCause(),
                HttpStatus.UNAUTHORIZED
        );

        return new ResponseEntity<>(investorException, HttpStatus.UNAUTHORIZED);
    }
}
