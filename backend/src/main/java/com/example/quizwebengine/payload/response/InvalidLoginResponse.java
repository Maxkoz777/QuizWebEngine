package com.example.quizwebengine.payload.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {

    private String username;
    private String password;

    public InvalidLoginResponse() {
        username = "Invalid username";
        password = "Invalid password";
    }

}
