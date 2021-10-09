package com.example.quizwebengine.payload.response;

import com.example.quizwebengine.model.quiz.Quiz;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuizListResponse {

    private List<QuizDataResponse> quizzes;

    public QuizListResponse(List<Quiz> quizzesList) {
        quizzes = new ArrayList<>();
        quizzesList.forEach(quiz -> quizzes.add(
                new QuizDataResponse(quiz.getId(), quiz.getName(), quiz.getQuestions())
        ));
    }

}
