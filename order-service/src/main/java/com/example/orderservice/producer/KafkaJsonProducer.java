package com.example.orderservice.producer;

import com.example.orderservice.domain.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class KafkaJsonProducer {
    private final KafkaTemplate<String, OrderEntity> kafkaTemplate;

    public void sendOrder(OrderEntity order){
        System.out.println("주문하신 내역입니다.");
        kafkaTemplate.send("order_create",order);
    }
}
