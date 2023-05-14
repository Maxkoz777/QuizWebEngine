package com.example.quizwebengine.opentdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ExternalQuizDto {
    @JsonProperty("response_code")
    private String responseCode;
    @JsonProperty("results")
    private List<ExternalQuestionDto> questions;
}
