package com.example.quizwebengine.model.userInfo;

import com.example.quizwebengine.model.quiz.Quiz;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private Role role;

    @OneToMany
    private List<Quiz> quizzes;

}
