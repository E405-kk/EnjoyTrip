package com.ssafy.enjoytrip.chatgpt.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompletionRequestDto {
    private String model;
    private String prompt;
    private float temperature;
}
