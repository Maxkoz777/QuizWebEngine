package com.example.quizwebengine.testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.model.user_info.User;
import com.example.quizwebengine.repository.UserRepository;
import com.example.quizwebengine.service.QuizService;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    QuizService quizService;

    @Autowired
    UserRepository userRepository;

    @Container
    public static PostgreSQLContainer postrgresContainer = new PostgreSQLContainer();

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postrgresContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postrgresContainer::getPassword);
        registry.add("spring.datasource.username", postrgresContainer::getUsername);
        registry.add("spring.datasource.driverClassName", postrgresContainer::getDriverClassName);
    }

    private User stubUser() {
        User user = new User(1L, "userName", "user@mail.com", "pw", Collections.emptyList());
        user.setFirstname("firstname");
        user.setLastname("lastname");
        return user;
    }

    @Test
    @SneakyThrows
    void quizSavingTest() {
        int QUIZZES = 5;
        long userId = userRepository.save(stubUser()).getId();
        assertEquals(0, quizService.getListOfQuizzes(userId).size());
        for (int i = 0; i < QUIZZES; i++) {
            quizService.createQuizForUser(new Quiz(), userId);
        }
        assertEquals(QUIZZES, quizService.getListOfQuizzes(userId).size());
    }


}
