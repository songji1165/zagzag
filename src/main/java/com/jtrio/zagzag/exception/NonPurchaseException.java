package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class NonPurchaseException extends ApiException {
    public NonPurchaseException(String message){
        super(message);
    }

    public HttpStatus getStatus(){
        return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
    }
}


