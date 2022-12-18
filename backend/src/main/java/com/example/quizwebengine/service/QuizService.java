package com.example.quizwebengine.service;

import com.example.quizwebengine.exceptions.QuizException;
import com.example.quizwebengine.model.quiz.Quiz;
import java.util.List;

public interface QuizService {

    long createQuiz(Quiz quiz, Long userId) throws QuizException;

    Quiz getDataAboutQuiz(long quizId) throws QuizException;

    void updateQuizData(long quizId, String name) throws QuizException;

    void deleteQuizData(long quizId) throws QuizException;

    List<Quiz> getListOfQuizzes(long userId) throws QuizException;
}
