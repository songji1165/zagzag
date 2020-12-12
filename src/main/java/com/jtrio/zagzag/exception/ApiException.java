package com.jtrio.zagzag.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public abstract class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }

    abstract HttpStatus getStatus();
}
