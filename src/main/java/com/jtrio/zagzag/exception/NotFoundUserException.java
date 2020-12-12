package com.jtrio.zagzag.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@Data
//@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="no such user data") //404
public class NotFoundUserException extends ApiException {
//    private String message = "해당 사용자의 정보가 없습니다.";
    public NotFoundUserException(String message){
        super(message);
    }
        public HttpStatus getStatus(){
            return HttpStatus.NOT_FOUND;
        }
}


