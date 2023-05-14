package com.example.quizwebengine.service;

import com.example.quizwebengine.exceptions.QuizException;
import com.example.quizwebengine.model.quiz.Quiz;
import java.util.List;

public interface QuizService {

    /**
     *
     * @param quiz is a quiz
     * @param userId is a user id
     * @return id of created quiz
     * @throws QuizException incorrect quiz
     */
    long createQuiz(Quiz quiz, Long userId) throws QuizException;

    /**
     *
     * @param quizId is a quiz Id
     * @return quiz data
     * @throws QuizException no quiz found
     */
    Quiz getDataAboutQuiz(long quizId) throws QuizException;

    /**
     *
     * @param quizId quiz id
     * @param name quiz name
     * @throws QuizException no quiz found
     */
    void updateQuizData(long quizId, String name) throws QuizException;

    /**
     *
     * @param quizId quiz id
     * @throws QuizException no quiz found
     */
    void deleteQuizData(long quizId) throws QuizException;

    /**
     *
     * @param userId is a user id
     * @return list of all quizzes
     * @throws QuizException no user found
     */
    List<Quiz> getListOfQuizzes(long userId) throws QuizException;

    /**
     *
     * @return daily quiz
     */
    Quiz getDailyQuiz();

}
