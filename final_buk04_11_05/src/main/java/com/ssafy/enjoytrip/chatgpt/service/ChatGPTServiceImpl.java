package com.ssafy.enjoytrip.chatgpt.service;

import com.ssafy.enjoytrip.chatgpt.config.ChatGPTConfig;
import com.ssafy.enjoytrip.chatgpt.model.CompletionRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ChatGPTServiceImpl implements ChatGPTService {

    private final ChatGPTConfig chatGPTConfig;

    public ChatGPTServiceImpl(ChatGPTConfig chatGPTConfig) {
        this.chatGPTConfig = chatGPTConfig;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;


    @Override
    public String prompt(CompletionRequestDto completionRequestDto) {
        log.debug("[+] 프롬프트를 수행합니다.");
        try {
            String apiUrl = "https://api.openai.com/v1/chat/completions";

            HttpHeaders headers = chatGPTConfig.httpHeaders();
            JSONObject data = new JSONObject();
            data.put("model", model);
            data.put("temperature", 0.7);

            JSONObject message = new JSONObject();
            message.put("role", "user");
            message.put("content", completionRequestDto.getPrompt());

            JSONArray messages = new JSONArray();
            messages.put(message);

            data.put("messages", messages);

            HttpEntity<String> requestEntity = new HttpEntity<>(data.toString(), headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class);

            // JSON 문자열을 JSONObject로 변환하여 content 필드 값을 추출
            JSONObject jsonResponse = new JSONObject(response.getBody());
            JSONArray choices = jsonResponse.getJSONArray("choices");
            JSONObject firstChoice = choices.getJSONObject(0);
            String content = firstChoice.getJSONObject("message").getString("content");
            System.out.println(content);

            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
