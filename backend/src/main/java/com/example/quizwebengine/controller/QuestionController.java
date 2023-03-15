package com.example.quizwebengine.controller;

import com.example.quizwebengine.payload.request.QuestionRequest;
import com.example.quizwebengine.payload.response.CorrectAnswerResponse;
import com.example.quizwebengine.payload.response.MessageResponse;
import com.example.quizwebengine.payload.response.QuestionCreationResponse;
import com.example.quizwebengine.payload.response.QuestionResponse;
import com.example.quizwebengine.service.impl.QuestionService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("java:S1452")
@RestController
@CrossOrigin
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/{quizId}")
    public ResponseEntity<?> createQuestion(
            @Valid @RequestBody QuestionRequest questionRequest,
            @PathVariable Long quizId) {
        try {
            QuestionCreationResponse questionCreationResponse = new QuestionCreationResponse(
                    questionService.createQuestion(questionRequest, quizId)
            );
            return ResponseEntity.ok(questionCreationResponse);
        } catch (Throwable e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/{quizId}/list")
    public ResponseEntity<?> getListOfQuestions(@PathVariable Long quizId) {
        try {
            List<QuestionResponse> questionResponseList = questionService.getListOfQuestionsForTheQuiz(quizId);
            return ResponseEntity.ok(questionResponseList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<?> getDataAboutQuestion(@PathVariable(required = true) Long questionId) {
        try {
            QuestionResponse questionResponse = questionService.getDataAboutQuestion(questionId);
            return ResponseEntity.ok(questionResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<?> updateDataAboutQuestion(
            @Valid @RequestBody QuestionRequest questionRequest,
            @PathVariable Long questionId) {
        try {
            questionService.updateQuestion(questionRequest, questionId);
            return ResponseEntity.ok(new MessageResponse("Question has been updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteDataAboutQuestion(@PathVariable Long questionId) {
        try {
            questionService.deleteQuestion(questionId);
            return ResponseEntity.ok(new MessageResponse("Question has deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/{questionId}/correctAnswer")
    public ResponseEntity<?> checkCorrectnessOfAnswer(@PathVariable Long questionId) {
        try {
            return ResponseEntity.ok(new CorrectAnswerResponse(questionService.getCorrectAnswer(questionId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
