package com.example.quizwebengine.payload.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class QuestionResponse {

    private Long questionId;
    private String question;
    private Long rightAnswerId;
    private List<AnswerResponse> answer;

    public QuestionResponse(){
        this.answer = new ArrayList<>();
    }

}
