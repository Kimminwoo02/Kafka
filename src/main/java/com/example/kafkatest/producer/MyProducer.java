package com.example.kafkatest.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(){
        kafkaTemplate.send("mytest2","i'm hojin...");
    }

}
