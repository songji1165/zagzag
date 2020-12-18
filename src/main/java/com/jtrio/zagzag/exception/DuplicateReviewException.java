package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class DuplicateReviewException extends ApiException {

    public DuplicateReviewException(String message){
        super(message);
    }

    public HttpStatus getStatus(){
        return HttpStatus.BAD_REQUEST;
    }

}
