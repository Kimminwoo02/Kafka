package com.example.streamsink.service.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

//@Service
@RequiredArgsConstructor
public class KafkaConsumer1 {

    // 메세지가 브로커로부터 전송되면 필터링해서 topicB로 publish
    private final KafkaTemplate kafkaTemplate;
    @KafkaListener(topics = "topicA",groupId = "jsontestgroup", containerFactory = "getKafkalistenercontainer")
    public void receiveMessage(String message){
        System.out.println("^^^^^^^^^^^^");
        System.out.println(message);

        if(message.length() >= 5){
            kafkaTemplate.send("topicB",message);
            System.out.println(message);
        }
    }
}
