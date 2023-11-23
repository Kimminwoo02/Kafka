package com.example.streamsoruce.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class DefaultConsumer {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> getKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(jsonconsumerfactory());
        // 원하는 곳에서만 사용하려면 autoStart를 false로 설정한다.

        return containerFactory;
    }
    public ConsumerFactory<String, String> jsonconsumerfactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.0.5:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"jsontestgroup");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),new StringDeserializer());

    }
}
