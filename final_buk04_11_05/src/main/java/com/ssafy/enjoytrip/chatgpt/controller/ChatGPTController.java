package com.ssafy.enjoytrip.chatgpt.controller;


import com.ssafy.enjoytrip.chatgpt.model.CompletionRequestDto;
import com.ssafy.enjoytrip.chatgpt.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatgpt")
public class ChatGPTController {

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping("/prompt")
    public String prompt(@RequestBody CompletionRequestDto completionRequestDto) throws Exception {
        return chatGPTService.prompt(completionRequestDto);
    }
}
