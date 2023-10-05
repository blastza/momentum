package com.platform.momentum.exception.handler;

import com.platform.momentum.exception.UnauthorizedInvestorException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleException(IllegalStateException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleException() {
        return ResponseEntity
                .notFound()
                .build();
    }

    @ExceptionHandler(UnauthorizedInvestorException.class)
    public ResponseEntity<?> handleException(UnauthorizedInvestorException exception) {
        return ResponseEntity
                .status(401)
                .body(exception.getMessage());
    }
}
