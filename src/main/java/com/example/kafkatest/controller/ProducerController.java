package com.example.kafkatest.controller;

import com.example.kafkatest.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {
    TopicService topicService;

    @PostMapping("/message")
    public void publishMessage(@RequestParam String msg){
        topicService.send(msg);
    }
}
