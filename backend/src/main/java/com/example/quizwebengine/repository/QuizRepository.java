package com.example.quizwebengine.repository;

import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.model.userInfo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
