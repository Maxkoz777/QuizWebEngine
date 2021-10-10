package com.example.quizwebengine.model.quiz;

import com.example.quizwebengine.model.userInfo.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user;

    public Quiz(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
