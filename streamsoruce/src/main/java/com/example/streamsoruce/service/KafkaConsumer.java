package com.example.streamsoruce.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer  {
    @KafkaListener(topics = "topicB",groupId = "jsontestgroup", containerFactory = "getKafkaListenerContainerFactory")
    public void getMessage(String message){
        System.out.println("============================");
        System.out.println("============================");
        System.out.println("============================" + message);

    }

}
