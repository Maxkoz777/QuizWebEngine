package com.example.quizwebengine.payload.response;

import com.example.quizwebengine.model.quiz.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuizDataResponse {

    private long quizId;
    private String name;
    private List<QuestionCreationResponse> questions;

    public QuizDataResponse(long quizId, String name, List<Question> questions) {
        this.quizId = quizId;
        this.name = name;
        questions.forEach(question -> this.questions.add(new QuestionCreationResponse(question.getId())));
    }
}
