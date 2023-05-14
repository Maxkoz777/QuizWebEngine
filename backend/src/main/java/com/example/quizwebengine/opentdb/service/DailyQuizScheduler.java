package com.example.quizwebengine.opentdb.service;

import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.opentdb.client.OpentdbClient;
import com.example.quizwebengine.opentdb.dto.ExternalQuestionDto;
import com.example.quizwebengine.opentdb.dto.ExternalQuizDto;
import com.example.quizwebengine.repository.QuizRepository;
import com.example.quizwebengine.service.impl.QuestionService;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DailyQuizScheduler {

    private final QuizRepository quizRepository;
    private final OpentdbClient opentdbClient;
    private final QuestionService questionService;
    private static final String DAILY_QUIZ_NAME = "Daily quiz";

    private Quiz quiz = null;

    @Scheduled(fixedRate = 1L, timeUnit = TimeUnit.DAYS)
    protected void scheduledDailyQuizUpdate() {
        log.info("Updating daily quiz");
        if (Objects.nonNull(quiz)) {
            quizRepository.delete(quiz);
        }
        updateDailyQuiz();
    }

    void updateDailyQuiz() {
        ExternalQuizDto externalQuiz = opentdbClient.getExternalQuiz();
        log.info("External quiz retrieved successfully");
        long quizId = quizRepository.save(new Quiz(DAILY_QUIZ_NAME)).getId();
        externalQuiz.getQuestions().stream()
            .map(ExternalQuestionDto::getQuestionRequest)
            .forEach(question -> questionService.createQuestion(question, quizId));
        quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("quiz not found by id"));
        log.info("Daily quiz {} persisted to storage", quizId);
    }

    public Quiz getDailyQuiz() {
        return quiz;
    }

}
