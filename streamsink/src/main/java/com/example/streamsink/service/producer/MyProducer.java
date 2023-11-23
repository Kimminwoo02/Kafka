package com.example.streamsink.service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    public void send(String val){
        kafkaTemplate.send("springexam",val);
    }
    public void async(String topic,String message) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new KafkaSendCallback<>(){
            // 메세지가 실패하면 롤백으로 호출되는 메소드
            @Override
            public void onFailure(KafkaProducerException ex) {
                ProducerRecord<Object,Object> record = ex.getFailedProducerRecord();
                log.info("******************** exe"+ record);
            }


            // 메세지보내기가 성공하면 콜백으로 호출되는 메소드
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("******************** 성공 ");
            }
        });


    }

}
