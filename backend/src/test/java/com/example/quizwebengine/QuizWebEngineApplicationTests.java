package com.example.quizwebengine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuizWebEngineApplicationTests {

    @LocalServerPort
    private Integer port;

    @Test
    void contextLoads() {

    }

}
