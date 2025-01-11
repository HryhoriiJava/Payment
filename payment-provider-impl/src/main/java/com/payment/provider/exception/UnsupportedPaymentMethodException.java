package com.payment.provider.exception;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class UnsupportedPaymentMethodException extends RuntimeException {
    public UnsupportedPaymentMethodException() {
    }

    public UnsupportedPaymentMethodException(String message) {
        super(getNotFoundMessage(message));
    }

    public UnsupportedPaymentMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    private static String getNotFoundMessage(@Nullable String message) {
        return "Unsupported payment method: " + message;
    }

}
