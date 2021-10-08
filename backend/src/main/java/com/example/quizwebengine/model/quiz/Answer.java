package com.example.quizwebengine.model.quiz;

import com.example.quizwebengine.payload.request.AnswerRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(AnswerRequest answerRequest) {
        text = answerRequest.getAnswerText();
    }

}
