package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class MissParameterException extends ApiException {

    public MissParameterException(String message){
        super(message);
    }

    public HttpStatus getStatus(){
        return HttpStatus.BAD_REQUEST;
    }

}
