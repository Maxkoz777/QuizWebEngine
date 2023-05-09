package com.example.quizwebengine.opentdb.client;

import com.example.quizwebengine.opentdb.dto.ExternalQuizDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpentdbClientImpl implements OpentdbClient{

    private final WebClient opentdbClient;


    @Override
    public ExternalQuizDto getExternalQuiz() {
        return opentdbClient.get()
            .retrieve()
            .bodyToMono(ExternalQuizDto.class).block();
    }
}
