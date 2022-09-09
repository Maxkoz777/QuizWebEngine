package com.example.quizwebengine.service;

import com.example.quizwebengine.model.quiz.Quiz;
import java.util.List;

public interface QuizService {

    long createQuiz(Quiz quiz, Long userId) throws Exception;

    Quiz getDataAboutQuiz(long quizId) throws Exception;

    void updateQuizData(long quizId, String name) throws Exception;

    void deleteQuizData(long quizId) throws Exception;

    List<Quiz> getListOfQuizzes(long userId) throws Exception;
}
