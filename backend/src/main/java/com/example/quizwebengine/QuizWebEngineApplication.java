package com.example.quizwebengine;

import com.example.quizwebengine.opentdb.service.DailyQuizScheduler;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class QuizWebEngineApplication {

    @Autowired
    DailyQuizScheduler dailyQuizScheduler;

    public static void main(String[] args) {
        SpringApplication.run(QuizWebEngineApplication.class, args);
    }

//    @PostConstruct
//    public void init() {
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> dailyQuizScheduler.removeAllPersistedQuizzes()));
//    }

}
