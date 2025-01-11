package com.payment.provider.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(UnsupportedCourierException.class)
    public ResponseEntity<?> handleUnsupportedCourierException(UnsupportedCourierException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }
    @ExceptionHandler(UnsupportedPaymentMethodException.class)
    public ResponseEntity<?> handleUnsupportedPaymentMethodException(UnsupportedPaymentMethodException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

}