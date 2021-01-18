package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class DuplicateDataException extends ApiException {
    public DuplicateDataException(String message){ super(message); }
    public HttpStatus getStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
