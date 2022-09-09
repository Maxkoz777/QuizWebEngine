package com.example.quizwebengine.model.quiz;

import com.example.quizwebengine.model.user_info.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "quiz")
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Question> questions;


    @ManyToOne
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user;

    public Quiz(String name) {
        this.name = name;
    }
}
