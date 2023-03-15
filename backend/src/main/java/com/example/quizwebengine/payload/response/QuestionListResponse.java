package com.example.quizwebengine.payload.response;

import java.util.List;
import lombok.Data;

@Data
public class QuestionListResponse {

    private List<QuestionResponse> questionList;

}
