package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends ApiException {

    public DuplicateEmailException(String message){
        super(message);
    }

    public HttpStatus getStatus(){
        return HttpStatus.BAD_REQUEST;
    }

}
