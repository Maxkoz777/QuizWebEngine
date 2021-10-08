package com.example.quizwebengine.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class QuestionListResponse {

    private List<QuestionResponse> questionList;

}
