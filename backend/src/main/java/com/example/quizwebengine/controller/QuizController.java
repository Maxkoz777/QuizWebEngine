package com.example.quizwebengine.controller;

import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.payload.request.QuizCreationRequest;
import com.example.quizwebengine.payload.response.MessageResponse;
import com.example.quizwebengine.payload.response.QuizCreationResponse;
import com.example.quizwebengine.payload.response.QuizDataResponse;
import com.example.quizwebengine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quiz")
    public ResponseEntity<?> createNewQuiz(@Valid @RequestBody QuizCreationRequest quizCreation) {
        Quiz quiz = new Quiz(quizCreation.getName());
        long quizId = quizService.createQuiz(quiz);
        return ResponseEntity.ok(new QuizCreationResponse(quizId));
    }

    @GetMapping("/quiz/{quizId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getDataAboutQuiz(@PathVariable(required = true) Long quizId) {
        try {
            Quiz quiz = quizService.getDataAboutQuiz(quizId);
            QuizDataResponse quizData = new QuizDataResponse(quizId, quiz.getName(), quiz.getQuestions());
            return ResponseEntity.ok(quizData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PutMapping("/quiz/{quizId}")
    public ResponseEntity<?> updateQuizData(@PathVariable(required = true) Long quizId,
                                            @Valid @RequestBody QuizCreationRequest quizUpdateData) {
        try {
            quizService.updateQuizData(quizId, quizUpdateData.getName());
            return ResponseEntity.ok(new MessageResponse("Quiz has been updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/quiz/{quizId}")
    public ResponseEntity<?> deleteQuizData(@PathVariable(required = true) Long quizId) {
        try {
            quizService.deleteQuizData(quizId);
            return ResponseEntity.ok(new MessageResponse("Quiz has been deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
