package com.example.quizwebengine.controller;

import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.payload.request.QuizCreationRequest;
import com.example.quizwebengine.payload.response.MessageResponse;
import com.example.quizwebengine.payload.response.QuizCreationResponse;
import com.example.quizwebengine.payload.response.QuizDataResponse;
import com.example.quizwebengine.payload.response.QuizListResponse;
import com.example.quizwebengine.security.JWTAuthenticationFilter;
import com.example.quizwebengine.service.impl.QuizServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/quiz")
public class QuizController {

    private final QuizServiceImpl quizService;

    @Autowired
    public QuizController(QuizServiceImpl quizService) {
        this.quizService = quizService;
    }

    @PostMapping()
    public ResponseEntity<?> createNewQuiz(HttpServletRequest request,
                                           @Valid @RequestBody QuizCreationRequest quizCreation) {
        try {
            Long userId = (Long) request.getAttribute(JWTAuthenticationFilter.USER_ID_KEY);
            Quiz quiz = new Quiz(quizCreation.getName());
            long quizId = quizService.createQuiz(quiz, userId);
            return ResponseEntity.ok(new QuizCreationResponse(quizId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/{quizId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getDataAboutQuiz(@PathVariable Long quizId) {
        try {
            Quiz quiz = quizService.getDataAboutQuiz(quizId);
            QuizDataResponse quizData = new QuizDataResponse(quizId, quiz.getName(), quiz.getQuestions());
            return ResponseEntity.ok(quizData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<?> updateQuizData(@PathVariable Long quizId,
                                            @Valid @RequestBody QuizCreationRequest quizUpdateData) {
        try {
            quizService.updateQuizData(quizId, quizUpdateData.getName());
            return ResponseEntity.ok(new MessageResponse("Quiz has been updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<?> deleteQuizData(@PathVariable Long quizId) {
        try {
            quizService.deleteQuizData(quizId);
            return ResponseEntity.ok(new MessageResponse("Quiz has been deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListOfQuizzesForUser(HttpServletRequest request) {
        try {
            Long userId = (Long) request.getAttribute(JWTAuthenticationFilter.USER_ID_KEY);
            return ResponseEntity.ok(new QuizListResponse(quizService.getListOfQuizzes(userId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/daily")
    public ResponseEntity<QuizDataResponse> getDailyQuiz() {
        Quiz quiz = quizService.getDailyQuiz();
        QuizDataResponse quizData = new QuizDataResponse(quiz.getId(), quiz.getName(), quiz.getQuestions());
        return ResponseEntity.ok(quizData);
    }

}
