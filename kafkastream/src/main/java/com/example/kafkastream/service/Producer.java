package com.example.kafkastream.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import static com.example.kafkastream.simpleStreamTest.getProperties;

@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {
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

                StreamsBuilder builder = new StreamsBuilder();
                // source-topic의 데이터를 읽어와 KStream으로 변경
                KStream<String,String> sourceStream = builder.stream("topicA");

                // 데이터변환 후
                // sink-topic으로 처리가 완료된 데이터 전송
                // 입력된 글자 수가 4글자 미만 일 경우 싱크로
                log.info("여기까지 오나??");
                KStream<String,String> filterStream = sourceStream.filter((key, value) -> value.length() < 4);
                filterStream.to("sink-topic");

                // 스트림즈 작업을 시작.

                log.info("여기까지 오나????");
                KafkaStreams streams = new KafkaStreams(builder.build(),getProperties("stream-test1"));
                streams.start();
            }
        });


    }

}


