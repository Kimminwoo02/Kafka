package com.example.kafkatest;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTestApplication.class, args);
    }

    // spring boot Application이 실행되고 나서 특정 작업을 바로 실행할 수 있도록 기능
    @Bean
    public ApplicationRunner runner (KafkaTemplate<String,String> kafkaTemplate){
        return args -> {
            // 실행할 내용을 정의 -
            // 실행되면서 바로 카프카의 mytest2 토픽에 메세지를 전송
            // kafka 프로듀서는 kafkaTemplate을 이용해서 생성
            // Key : 토픽명 ,
            kafkaTemplate.send("mytest2","im hojin");
        };
    }

}
