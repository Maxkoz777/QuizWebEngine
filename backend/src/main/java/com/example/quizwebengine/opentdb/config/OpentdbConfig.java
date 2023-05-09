package com.example.quizwebengine.opentdb.config;

import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@EnableConfigurationProperties(OpentdbProperties.class)
public class OpentdbConfig {

    private final OpentdbProperties opentdbProperties;

    @Bean(name = {"opentdbClient"})
    public WebClient blockchainWebClient() {
        return WebClient.builder()
            .baseUrl(opentdbProperties.getBaseUrl())
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name())
            .build();
    }

}
