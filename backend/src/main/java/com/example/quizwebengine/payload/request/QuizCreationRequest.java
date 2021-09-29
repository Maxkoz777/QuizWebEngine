package com.example.quizwebengine.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class QuizCreationRequest {

    @NotEmpty(message = "Name of quiz is required")
    private String name;

}
