//package com.example.orderservice.config;
//
//import com.example.orderservice.domain.OrderEntity;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaTemplateConfig {
//    @Bean
//    public KafkaTemplate<String, OrderEntity> kafkaTemplate(){
//        return new KafkaTemplate<>(producerFactory());
//
//    }
//
//    //Producer객체를 만들 수 있도록 ProducerFactory 객체 생성
//    public ProducerFactory<String,OrderEntity> producerFactory(){
//        return new DefaultKafkaProducerFactory<>(producerPros());// ProduycerFactory의 구현체를 생성
//    }
//    private Map<String,Object> producerPros(){
//        Map<String,Object> props = new HashMap<>();
//
//        // 서버
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.0.5:9092");
//        // 키 - key를 직렬화할 수 있는 Serializer 객체타입으로 지정
//        //           ---- 문자열을 패킷단위로 쪼개서 전송할 수 있는 타입으로 만듬
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//
//        // 밸류
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        return props;
//    }
//}
