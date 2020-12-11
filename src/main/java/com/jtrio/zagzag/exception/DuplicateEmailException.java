package com.jtrio.zagzag.exception;

import lombok.Data;

@Data
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message){
        super(message);
    }

}
