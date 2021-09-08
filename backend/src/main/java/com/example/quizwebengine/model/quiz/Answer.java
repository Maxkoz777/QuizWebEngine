package com.example.quizwebengine.model.quiz;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String text;

}
