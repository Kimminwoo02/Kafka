package com.example.streamsink.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyConsumer implements MessageListener<String,String> {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener( topics = "topicA", containerFactory = "getKafkaListenerContainerFactory")
    public void onMessage(ConsumerRecord<String, String> data) {
        log.info("listner========>  message " + data);
        log.info("listner========>  message " + data.key());
        log.info("listner========>  message " + data.value());


        if( data.value().length() >= 5){
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("topicB", data.value());
            future.addCallback(new KafkaSendCallback<>(){
                // 메세지가 실패하면 롤백으로 호출되는 메소드
                @Override
                public void onFailure(KafkaProducerException ex) {
                    ProducerRecord<Object,Object> record = ex.getFailedProducerRecord();
                    log.info("***실패 ㅠㅠ exe"+ record);
                }


                // 메세지보내기가 성공하면 콜백으로 호출되는 메소드
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("******************** 성공 ");
                }
            });
        }

    }


}
