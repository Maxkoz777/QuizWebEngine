package com.example.quizwebengine.repository;

import com.example.quizwebengine.model.quiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
