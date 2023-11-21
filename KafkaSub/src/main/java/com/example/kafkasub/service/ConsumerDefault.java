package com.example.kafkasub.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerDefault implements MessageListener<String, String> {
    // 메시지가 전송되면 onMessage가 호출되고 데이터가 ConsumerRecord로 전달된다..
    // Record로 전달되는 방식은 데이터가 한 건씩 전달되는 방식 (단일 메시지 처리방식)


    @KafkaListener(id="msa-id", topics = "kafkaexam2", containerFactory = "containerFactory")
    public void onMessage(ConsumerRecord<String, String> data) {
        System.out.println("메시지 출력 : "+ data.value());
        System.out.println("메시지 출력 완료!");
    }
}
