package com.example.quizwebengine.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {

    private String question;
    private List<AnswerRequest> answer;

}
