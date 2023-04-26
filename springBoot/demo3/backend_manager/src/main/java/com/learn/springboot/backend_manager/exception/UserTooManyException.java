package com.learn.springboot.backend_manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User Too Many")
public class UserTooManyException extends RuntimeException {

    public UserTooManyException(String arg0) {
        super(arg0);
    }
    
    
}
