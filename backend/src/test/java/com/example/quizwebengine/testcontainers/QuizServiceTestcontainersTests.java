package com.example.quizwebengine.testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers
@SpringBootTest
@RequiredArgsConstructor
class QuizServiceTestcontainersTests {

    @Autowired
    QuizRepository quizRepository;

    @Container
    public static PostgreSQLContainer postrgresContainer = new PostgreSQLContainer();

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postrgresContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postrgresContainer::getPassword);
        registry.add("spring.datasource.username", postrgresContainer::getUsername);
    }

    @Test
    void quizSavingTest() {
        int QUIZZES = 5;
        assertEquals(0, quizRepository.findAll().size());
        for (int i = 0; i < QUIZZES; i++) {
            quizRepository.save(new Quiz());
        }
        assertEquals(QUIZZES, quizRepository.findAll().size());
    }


}
