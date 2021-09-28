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
        return quizRepository.findById(quizId).orElseThrow(() -> new Exception("No such quiz"));
    }

}
