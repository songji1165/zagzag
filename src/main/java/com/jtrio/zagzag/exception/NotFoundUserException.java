package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="no such user data") //404
public class NotFoundUserException extends RuntimeException {
//    private String message = "해당 사용자의 정보가 없습니다.";
    public NotFoundUserException(String message){
        super(message);
    }
}


