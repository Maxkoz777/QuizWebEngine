package com.example.quizwebengine.opentdb.dto;

import com.example.quizwebengine.payload.request.AnswerRequest;
import com.example.quizwebengine.payload.request.QuestionRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ExternalQuestionDto {

    private String category;
    private String type;
    private String difficulty;
    private String question;
    @JsonProperty("correct_answer")
    private String correctAnswer;
    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers;

    private List<AnswerRequest> getAnswerRequests() {
        List<AnswerRequest> answerRequestList = new ArrayList<>();
        answerRequestList.add(new AnswerRequest(correctAnswer, true));
        incorrectAnswers.stream()
            .map(answer -> new AnswerRequest(answer, false))
            .forEach(answerRequestList::add);
        return answerRequestList;
    }

    public QuestionRequest getQuestionRequest() {
        return new QuestionRequest(question, getAnswerRequests());
    }

}
