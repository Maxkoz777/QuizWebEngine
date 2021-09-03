package com.example.quizwebengine.model.userInfo;

import com.example.quizwebengine.model.quiz.Quiz;
import lombok.Data;

import java.util.List;

@Data
public class User {

    private long id;

    private String alias;

    private String password;

    private Role role;

    private Statistics statistics;

    private ContactInfo contactInfo;

    private List<Quiz> createdQuizzes;

}
