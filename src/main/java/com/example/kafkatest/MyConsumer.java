package com.example.kafkatest;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class MyConsumer {

    // 카프카가 메소드를 호출하면서 매개변수로 전송된 데이터를 전달
    // @KafkaListener 어노테이션을 추가하면 카프카 컨슈머의 역할을 한다.
    @KafkaListener(id = "msa-id" ,topics="mytest2")
    public void listen(String message){
        System.out.println("==================");
        System.out.println(message);
    }

}
