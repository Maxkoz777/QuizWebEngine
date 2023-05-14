package com.example.quizwebengine.repository;

import com.example.quizwebengine.model.quiz.Quiz;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Optional<List<Quiz>> findAllByUserId(Long id);

}
