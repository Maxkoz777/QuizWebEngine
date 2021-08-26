package com.example.quizwebengine.model.quiz;

import lombok.Data;

@Data
public class Question {

    private long id;

    private String text;

    private Answer[] answers;

    private QuestionType questionType;

    private boolean isAgeLimited;

    private Quiz quiz;

}
