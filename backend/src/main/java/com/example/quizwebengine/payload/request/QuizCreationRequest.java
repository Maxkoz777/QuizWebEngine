package com.example.quizwebengine.payload.request;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class QuizCreationRequest {

    @NotEmpty(message = "Name of quiz is required")
    private String name;

}
