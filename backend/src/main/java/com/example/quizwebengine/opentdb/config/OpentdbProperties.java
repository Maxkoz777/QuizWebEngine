package com.example.quizwebengine.opentdb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("opentdb")
public class OpentdbProperties {

    private String baseUrl;

}
