package com.example.quizwebengine.model.quiz;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;

    @OneToMany
    private List<Answer> answers;

    @OneToOne
    private Answer rightAnswer;

}
