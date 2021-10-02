package com.example.quizwebengine.service;

import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.model.userInfo.User;
import com.example.quizwebengine.repository.QuizRepository;
import com.example.quizwebengine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    public long createQuiz(Quiz quiz, Long userId) throws Exception {
        User user = userRepository.findUserById(userId).orElseThrow(() -> new Exception("No quiz with such id"));
        quiz.setUser(user);
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

    public List<Quiz> getListOfQuizzes(long userId) throws Exception {
        return quizRepository.findAllByUserId(userId).orElseThrow(() -> new Exception("No user with such id"));
    }

}
