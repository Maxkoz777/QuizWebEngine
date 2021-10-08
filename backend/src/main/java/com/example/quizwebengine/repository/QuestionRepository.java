package com.example.quizwebengine.repository;

import com.example.quizwebengine.model.quiz.Question;
import com.example.quizwebengine.model.userInfo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<List<Question>> findAllByQuizId(Long quizId);

}
