package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class ParameterMissedException extends ApiException {

    public ParameterMissedException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

}
