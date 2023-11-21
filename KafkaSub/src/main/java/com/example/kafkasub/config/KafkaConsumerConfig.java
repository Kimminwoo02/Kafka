package com.example.kafkasub.config;

import com.example.kafkasub.service.ConsumerDefault;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> containerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public KafkaMessageListenerContainer<String,String> makeListener(){
        ContainerProperties properties = new ContainerProperties("springexam");

        // 설정정보 셋팅 후
        // kafkaMessageListenerContainer를 이용해서 작업하는 경우 그룹아이디는 반드시 정의해야 한다.
        // 그룹아이디가 있어야 클라이언트 아이디가 생성되고 서버에서 인지될 수 있다.

        properties.setGroupId("springexamgroup");
        properties.setAckMode(ContainerProperties.AckMode.BATCH);
        properties.setMessageListener(new ConsumerDefault());

        KafkaMessageListenerContainer container = new KafkaMessageListenerContainer(consumerFactory(),properties);

        // 원하는 시점에 start하기 위해서 자동으로 start 하지 않도록 설정
        container.setAutoStartup(false);
        return container;
    }


    // 컨슈머를 위한 설정정보를 정의
    public ConsumerFactory<String,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }
    private Map<String,Object> consumerProps(){
        Map<String,Object> props = new HashMap<>();
        // 토픽에 저장된 데이터를 가져올 때 필요한 설정(네트워크로 전송되어 들어오는 데이터를 역직렬화)

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.0.5:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        return props;
    }


}
