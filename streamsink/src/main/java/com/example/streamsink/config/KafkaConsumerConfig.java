package com.example.streamsink.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> getKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory());
        // 원하는 곳에서만 사용하려면 autoStart를 false로 설정한다.

        return containerFactory;
    }
    public ConsumerFactory<String, String> consumerFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.0.5:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"jsontestgroup");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),new StringDeserializer());

    }
}