package com.example.quizwebengine.model.quiz;

import com.example.quizwebengine.payload.request.AnswerRequest;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
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
