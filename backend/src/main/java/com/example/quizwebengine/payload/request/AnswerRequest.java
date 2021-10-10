package com.example.quizwebengine.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequest {

    private String answerText;
    private Boolean isRight;

}
