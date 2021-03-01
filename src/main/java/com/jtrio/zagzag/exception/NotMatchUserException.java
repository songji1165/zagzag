package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class NotMatchUserException extends ApiException {
    public NotMatchUserException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
