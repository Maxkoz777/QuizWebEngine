package com.example.quizwebengine.service.impl;

import com.example.quizwebengine.constants.ExceptionsTextConstants;
import com.example.quizwebengine.exceptions.QuizException;
import com.example.quizwebengine.model.quiz.Answer;
import com.example.quizwebengine.model.quiz.Question;
import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.payload.request.QuestionRequest;
import com.example.quizwebengine.payload.response.AnswerResponse;
import com.example.quizwebengine.payload.response.QuestionResponse;
import com.example.quizwebengine.repository.AnswerRepository;
import com.example.quizwebengine.repository.QuestionRepository;
import com.example.quizwebengine.repository.QuizRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;

    public Long createQuestion(QuestionRequest questionRequest, Long quizId) {
        Question question = new Question();
        question.setText(questionRequest.getQuestion());
        createAnswersForQuestion(questionRequest, question);
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException(ExceptionsTextConstants.NO_QUIZ_WITH_SUCH_ID));
        question.setQuiz(quiz);
        question = questionRepository.save(question);
        return question.getId();
    }

    public List<QuestionResponse> getListOfQuestionsForTheQuiz(Long quizId) throws Exception {
        return questionRepository.findAllByQuizId(quizId)
                .orElseThrow(() -> new Exception(ExceptionsTextConstants.NO_QUIZ_WITH_SUCH_ID))
                .stream().map(question -> {
                    QuestionResponse questionResponse = new QuestionResponse();
                    questionResponse.setQuestionId(question.getId());
                    questionResponse.setQuestion(question.getText());
                    questionResponse.setRightAnswerId(question.getRightAnswer().getId());
                    questionResponse.setAnswer(question.getAnswers().stream()
                                                   .map(answer -> new AnswerResponse(answer.getId(), answer.getText()))
                                                   .collect(Collectors.toList()));
                    return questionResponse;
                }).collect(Collectors.toList());
    }

    private void createAnswersForQuestion(QuestionRequest questionRequest, Question question) {
        questionRequest.getAnswer().forEach(answerRequest -> {
            Answer answer = new Answer(answerRequest);
            answer.setQuestion(question);
            question.getAnswers().add(answer);
            if (Boolean.TRUE.equals(answerRequest.getIsRight())) {
                question.setRightAnswer(answer);
            }
        });
    }

    public QuestionResponse getDataAboutQuestion(Long questionId) throws Exception {
        QuestionResponse questionResponse = new QuestionResponse();
        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new Exception("No question with such id"));
        questionResponse.setQuestionId(questionId);
        questionResponse.setQuestion(question.getText());
        questionResponse.setRightAnswerId(question.getRightAnswer().getId());
        question.getAnswers().forEach(answer -> questionResponse.getAnswer().add(new AnswerResponse(answer.getId(), answer.getText())));
        return questionResponse;
    }

    @SneakyThrows
    @Transactional
    public void updateQuestion(QuestionRequest questionRequest, Long questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        Question question;
        if (questionOptional.isPresent()) {
            question = questionOptional.get();
        } else {
            throw new QuizException("No questions exists with such id");
        }
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
        return question.getRightAnswer().getId();
    }

}
