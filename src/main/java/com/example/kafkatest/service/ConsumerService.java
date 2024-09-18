package com.example.kafkatest.service;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    
    @KafkaListener( topics = "ecommerce", groupId = "spring")
    public void consumer (String message){
        System.out.println(String.format("Subscribed :  %s", message));
    }


}
