package com.example.quizwebengine.repository;

import com.example.quizwebengine.model.quiz.Answer;
import com.example.quizwebengine.model.quiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    void deleteAllByQuestion(Question question);

}
