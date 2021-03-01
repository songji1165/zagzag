package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomErrorHandler {

//    @ExceptionHandler(DuplicateEmailException.class)
//    public ResponseEntity handleError(Exception e){
//        return null;
//    }


    @ExceptionHandler(ApiException.class)
    public ResponseEntity handleError(ApiException e) {
//        if(e instanceof DuplicateEmailException){
//            new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }else if(e instanceof NotFoundException){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
}
