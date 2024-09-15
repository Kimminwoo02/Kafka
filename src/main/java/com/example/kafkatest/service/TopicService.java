package com.example.kafkatest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    String topic = "Ecommerce";

    private KafkaTemplate<String,Object> kafkaTemplate;
    @Autowired
    public TopicService(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void send(String msg){
        kafkaTemplate.send(topic,msg);
    }
}
