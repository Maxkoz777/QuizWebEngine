package com.example.quizwebengine.payload.request;

import lombok.Data;

@Data
public class AnswerRequest {

    private String answerText;
    private Boolean isRight;

}
