package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

//@Data
public class CanNotUsedException extends ApiException {
//    private String message = "해당 사용자의 정보가 없습니다.";
    public CanNotUsedException(String message){
        super(message);
    }

    public HttpStatus getStatus(){
            return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        }
}


