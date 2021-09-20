package com.example.quizwebengine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExistException extends RuntimeException {
    public UserExistException(String s) {
    }
}
