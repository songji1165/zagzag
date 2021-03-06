package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class FailedChangeException extends ApiException {
    public FailedChangeException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}


