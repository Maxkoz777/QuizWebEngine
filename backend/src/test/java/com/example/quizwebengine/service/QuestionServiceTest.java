package com.example.quizwebengine.service;

import com.example.quizwebengine.model.quiz.Answer;
import com.example.quizwebengine.model.quiz.Question;
import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.payload.request.AnswerRequest;
import com.example.quizwebengine.payload.request.QuestionRequest;
import com.example.quizwebengine.repository.AnswerRepository;
import com.example.quizwebengine.repository.QuestionRepository;
import com.example.quizwebengine.repository.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    QuestionRepository questionRepository;

    @Mock
    QuizRepository quizRepository;

    @Mock
    AnswerRepository answerRepository;

    @InjectMocks
    QuestionService service;

    Question question;
    QuestionRequest questionRequest;
    Quiz quiz;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.setId(1L);
        question.setText("QuestionText");
        Answer answer = new Answer();
        answer.setId(2L);
        question.setRightAnswerId(answer.getId());

        questionRequest = new QuestionRequest();
        questionRequest.setQuestion("QuestionUpdatedText");

        AnswerRequest right = new AnswerRequest("1", true);
        AnswerRequest wrong = new AnswerRequest("2", false);

        questionRequest.setAnswer(Arrays.asList(right, wrong));

        quiz = new Quiz();
        quiz.setId(3L);

    }

    @Test
    void createQuestion() throws Exception {
        when(quizRepository.findById(3L)).thenReturn(java.util.Optional.ofNullable(quiz));

        Question questionStub = new Question();
        questionStub.setQuiz(quiz);
        questionStub.setId(null);
        questionStub.setText(questionRequest.getQuestion());
        questionStub.setRightAnswerId(null);

        when(questionRepository.save(questionStub)).thenReturn(questionStub);

        Long newQuestionId = service.createQuestion(questionRequest, 3L);

        assertAll(
                () -> verify(quizRepository).findById(3L),
                () -> verify(questionRepository).save(questionStub),
                () -> assertNull(newQuestionId)
        );

    }

    @Test
    void getListOfQuestionsForTheQuiz() {
    }

    @Test
    void getDataAboutQuestion() {
    }

    @Test
    void updateQuestion() {

        when(questionRepository.getById(1L)).thenReturn(question);

        service.updateQuestion(questionRequest, 1L);

        assertAll(
                () -> verify(answerRepository).deleteAllByQuestion(question),
                () -> verify(questionRepository).save(question)
        );
    }

    @Test
    void deleteQuestion() {
        service.deleteQuestion(1L);
        verify(questionRepository).deleteById(1L);
    }

    @Test
    void getCorrectAnswerSuccess() {
        assertAll(
                () -> {
                    when(questionRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(question));
                    Long rightAnswerId = service.getCorrectAnswer(1L);
                    verify(questionRepository).findById(1L);
                    assertEquals(2L, rightAnswerId);
                },
                () -> assertThrows(Exception.class, () -> service.getCorrectAnswer(2L))
        );

    }
}