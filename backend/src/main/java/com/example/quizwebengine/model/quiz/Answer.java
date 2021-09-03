package com.example.quizwebengine.model.quiz;

import lombok.Data;

@Data
public class Answer {

    private int id;

    private String answer;

    private boolean isCorrect;

}
