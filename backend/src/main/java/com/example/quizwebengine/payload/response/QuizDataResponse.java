package com.example.quizwebengine.payload.response;

import com.example.quizwebengine.model.quiz.Question;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDataResponse {

    private long quizId;
    private String name;
    private List<QuestionCreationResponse> questions;

    public QuizDataResponse(long quizId, String name, List<Question> questions) {
        this.quizId = quizId;
        this.name = name;
        this.questions = new ArrayList<>();
        questions.forEach(question -> this.questions.add(new QuestionCreationResponse(question.getId())));
    }
}
