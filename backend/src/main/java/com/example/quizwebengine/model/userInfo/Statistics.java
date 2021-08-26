package com.example.quizwebengine.model.userInfo;

import com.example.quizwebengine.model.quiz.QuestionType;
import lombok.Data;

@Data
public class Statistics {

    private long id;

    private long numberOfGames;

    private long numberOfWins;

    private float answerAccuracy;

    private QuestionType favouriteGameType;

    private long quizzesCreated;

    private long correctAnswers;

    private float averageTime;


}
