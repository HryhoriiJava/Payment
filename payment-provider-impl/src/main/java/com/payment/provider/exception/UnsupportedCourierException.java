package com.payment.provider.exception;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(BAD_REQUEST)
public class UnsupportedCourierException extends RuntimeException {
    public UnsupportedCourierException() {
    }

    public UnsupportedCourierException(String message) {
        super(getNotFoundMessage(message));
    }

    public UnsupportedCourierException(String message, Throwable cause) {
        super(message, cause);
    }

    private static String getNotFoundMessage(@Nullable String message) {
        return "Unsupported Courier: " + message;
    }

}
