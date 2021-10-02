package com.example.quizwebengine.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerResponse {

    public Long answerId;
    public String answerText;

}
