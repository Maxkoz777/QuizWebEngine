package com.example.quizwebengine.controller;

import com.example.quizwebengine.payload.request.QuestionRequest;
import com.example.quizwebengine.payload.response.CorrectAnswerResponse;
import com.example.quizwebengine.payload.response.MessageResponse;
import com.example.quizwebengine.payload.response.QuestionResponse;
import com.example.quizwebengine.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/question/{quizId}")
    public ResponseEntity<?> createQuestion(@Valid @RequestBody QuestionRequest questionRequest, @PathVariable Long quizId) {
        try {
            Long id = questionService.createQuestion(questionRequest, quizId);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<?> getDataAboutQuestion(@PathVariable(required = true) Long questionId) {
        try {
            QuestionResponse questionResponse = questionService.getDataAboutQuestion(questionId);
            return ResponseEntity.ok(questionResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PutMapping("/question/{questionId}")
    public ResponseEntity<?> updateDataAboutQuestion(@Valid @RequestBody QuestionRequest questionRequest, @PathVariable(required = true) Long questionId) {
        try {
            questionService.updateQuestion(questionRequest, questionId);
            return ResponseEntity.ok(new MessageResponse("Question has been updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/question/{questionId}")
    public ResponseEntity<?> deleteDataAboutQuestion(@PathVariable(required = true) Long questionId) {
        try {
            questionService.deleteQuestion(questionId);
            return ResponseEntity.ok(new MessageResponse("Question has deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/question/{questionId}/correctAnswer")
    public ResponseEntity<?> checkCorrectnessOfAnswer(@PathVariable(required = true) Long questionId) {
        try {
            return ResponseEntity.ok(new CorrectAnswerResponse(questionService.getCorrectAnswer(questionId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
