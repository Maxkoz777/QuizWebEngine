package com.example.quizwebengine.service;

import com.example.quizwebengine.model.quiz.Answer;
import com.example.quizwebengine.model.quiz.Question;
import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.payload.request.AnswerRequest;
import com.example.quizwebengine.payload.request.QuestionRequest;
import com.example.quizwebengine.payload.response.AnswerResponse;
import com.example.quizwebengine.payload.response.QuestionResponse;
import com.example.quizwebengine.repository.AnswerRepository;
import com.example.quizwebengine.repository.QuestionRepository;
import com.example.quizwebengine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuizRepository quizRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
        this.answerRepository = answerRepository;
    }

    public Long createQuestion(QuestionRequest questionRequest, Long quizId) throws Exception {
        Question question = new Question();
        question.setText(questionRequest.getQuestion());
        createAnswersForQuestion(questionRequest, question);
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new Exception("No quiz with such id"));
        question.setQuiz(quiz);
        question = questionRepository.save(question);
        return question.getId();
    }

    public List<QuestionResponse> getListOfQuestionsForTheQuiz(Long quizId) throws Exception {
        return questionRepository.findAllByQuizId(quizId)
                .orElseThrow(() -> new Exception("No question with such id"))
                .stream().map(question -> {
                    QuestionResponse questionResponse = new QuestionResponse();
                    questionResponse.setQuestionId(question.getId());
                    questionResponse.setQuestion(question.getText());
                    questionResponse.setRightAnswerId(question.getRightAnswerId());
                    questionResponse.setAnswer(question.getAnswers()
                            .stream().map(answer -> new AnswerResponse(answer.getId(), answer.getText()))
                            .collect(Collectors.toList()));
                    return questionResponse;
                }).collect(Collectors.toList());
    }

    private void createAnswersForQuestion(QuestionRequest questionRequest, Question question) {
        questionRequest.getAnswer().forEach(answerRequest -> {
            Answer answer = new Answer(answerRequest);
            answer.setQuestion(question);
            question.getAnswers().add(answer);
            if (answerRequest.getIsRight()) {
                question.setRightAnswerId(answer.getId());
            }
        });
    }

    public QuestionResponse getDataAboutQuestion(Long questionId) throws Exception {
        QuestionResponse questionResponse = new QuestionResponse();
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new Exception("No question with such id"));
        questionResponse.setQuestionId(questionId);
        questionResponse.setQuestion(question.getText());
        questionResponse.setRightAnswerId(question.getRightAnswerId());
        question.getAnswers().forEach(answer -> questionResponse.getAnswer().add(new AnswerResponse(answer.getId(), answer.getText())));
        return questionResponse;
    }

    @Transactional
    public void updateQuestion(QuestionRequest questionRequest, Long questionId) {
        Question question = questionRepository.getById(questionId);
        question.setText(questionRequest.getQuestion());
        answerRepository.deleteAllByQuestion(question);
        createAnswersForQuestion(questionRequest, question);
        questionRepository.save(question);
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public Long getCorrectAnswer(Long questionId) throws Exception {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new Exception("No question with such id"));
        return question.getRightAnswerId();
    }

}
