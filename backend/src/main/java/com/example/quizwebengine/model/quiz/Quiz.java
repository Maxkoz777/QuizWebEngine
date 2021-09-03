package com.example.quizwebengine.model.quiz;

import com.example.quizwebengine.model.userInfo.User;
import lombok.Data;

import java.util.List;

@Data
public class Quiz {

    private long id;

    private String title;

    private List<Question> questions;

    private User author;

}
