package com.example.kafkatest.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTemplateConfig {

    //KafkaTemplate 객체를 빈으로 등록
    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());

    }

    //Producer객체를 만들 수 있도록 ProducerFactory 객체 생성
    public ProducerFactory<String,String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerPros());// ProduycerFactory의 구현체를 생성
    }
    private Map<String,Object> producerPros(){
        Map<String,Object> props = new HashMap<>();

        // 카프카가 기본으로 제공하는 설정을 적용
        // 서버
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.0.114:9092");
        // 키 - key를 직렬화할 수 있는 Serializer 객체타입으로 지정
        //           ---- 문자열을 패킷단위로 쪼개서 전송할 수 있는 타입으로 만듬
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // 밸류
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        return props;
    }

}
