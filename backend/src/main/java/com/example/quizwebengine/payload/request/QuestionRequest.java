package com.example.quizwebengine.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class QuestionRequest {

    private String question;
    private List<AnswerRequest> answer;

}
