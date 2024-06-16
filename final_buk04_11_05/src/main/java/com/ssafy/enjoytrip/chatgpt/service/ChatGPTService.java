package com.ssafy.enjoytrip.chatgpt.service;

import com.ssafy.enjoytrip.chatgpt.model.CompletionRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ChatGPTService {

    String prompt(CompletionRequestDto completionRequestDto);

}
