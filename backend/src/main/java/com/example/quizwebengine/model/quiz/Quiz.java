package com.example.quizwebengine.model.quiz;

import lombok.Data;

@Data
public class Quiz {

    private long id;

    private String title;

    private Question[] questions;

}
