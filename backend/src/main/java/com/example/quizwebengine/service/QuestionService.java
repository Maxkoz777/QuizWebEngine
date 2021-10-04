package com.example.quizwebengine.service;

import com.example.quizwebengine.model.quiz.Answer;
import com.example.quizwebengine.model.quiz.Question;
import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.payload.request.QuestionRequest;
import com.example.quizwebengine.payload.response.AnswerResponse;
import com.example.quizwebengine.payload.response.QuestionResponse;
import com.example.quizwebengine.repository.QuestionRepository;
import com.example.quizwebengine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public Long createQuestion(QuestionRequest questionRequest, Long quizId) {
        Question question = new Question();
        question.setText(questionRequest.getQuestion());
        createAnswersForQuestion(questionRequest, question);
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        quiz.ifPresent(question::setQuiz);
        Question question1 = questionRepository.save(question);
        return question1.getId();
    }

    private void createAnswersForQuestion(QuestionRequest questionRequest, Question question) {
        questionRequest.getAnswer().forEach(answerRequest -> {
            Answer answer = new Answer(answerRequest);
            question.getAnswers().add(answer);
            if (answerRequest.getIsRight()) {
                question.setRightAnswer(answer);
            }
        });
    }

    public QuestionResponse getDataAboutQuestion(Long questionId) throws Exception {
        QuestionResponse questionResponse = new QuestionResponse();
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new Exception("No question with such id"));;
        questionResponse.setQuestionId(questionId);
        questionResponse.setQuestion(question.getText());
        question.getAnswers().forEach(answer -> {
            questionResponse.getAnswer().add(new AnswerResponse(answer.getId(), answer.getText()));
        });
        return questionResponse;
    }

    public void updateQuestion(QuestionRequest questionRequest, Long questionId) {
        Question question = questionRepository.getById(questionId);
        question.setText(questionRequest.getQuestion());
        createAnswersForQuestion(questionRequest, question);
        questionRepository.save(question);
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public Long getCorrectAnswer(Long questionId) throws Exception {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new Exception("No quiz with such id"));
        return question.getRightAnswer().getId();
    }

}
