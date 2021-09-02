package com.example.quizwebengine.model.quiz;

import lombok.Data;

import java.util.List;

@Data
public class Quiz {

    private long id;

    private String title;

    private List<Question> questions;

}
