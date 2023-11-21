package com.example.kafkaexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ExamProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg){
        kafkaTemplate.send("kafkaexam2",msg);
    }

}
