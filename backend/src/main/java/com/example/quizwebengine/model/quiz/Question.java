package com.example.quizwebengine.model.quiz;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private long id;

    private String text;

    private List<Answer> answers;

    private QuestionType questionType;

    private boolean isAgeLimited;

    private Quiz quiz;

}
