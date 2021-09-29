package com.example.quizwebengine.service;

import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public long createQuiz(Quiz quiz) {
        return quizRepository.save(quiz).getId();
    }

    public Quiz getDataAboutQuiz(long quizId) throws Exception {
        return quizRepository.findById(quizId).orElseThrow(() -> new Exception("No quiz with such id"));
    }

    public void updateQuizData(long quizId, String name) throws Exception {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new Exception("No quiz with such id"));
        quiz.setName(name);
        quizRepository.save(quiz);
    }

    public void deleteQuizData(long quizId) throws Exception {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new Exception("No quiz with such id"));
        quizRepository.delete(quiz);
    }

}
