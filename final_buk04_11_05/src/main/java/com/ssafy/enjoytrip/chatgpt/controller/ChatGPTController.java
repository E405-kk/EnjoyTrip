package com.ssafy.enjoytrip.chatgpt.controller;


import com.ssafy.enjoytrip.chatgpt.model.CompletionRequestDto;
import com.ssafy.enjoytrip.chatgpt.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatgpt")
@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE }, maxAge = 6000)
public class ChatGPTController {

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping("/prompt")
    public String prompt(@RequestBody CompletionRequestDto completionRequestDto) throws Exception {
        return chatGPTService.prompt(completionRequestDto);
    }
}
