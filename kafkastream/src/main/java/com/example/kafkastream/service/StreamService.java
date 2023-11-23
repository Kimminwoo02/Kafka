package com.example.kafkastream.service;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Service
public class StreamService {
    public static Properties getProperties(String app_id){
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,app_id);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.0.5:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        return props;
    }

    @Bean
    public void stream1(){

        StreamsBuilder builder = new StreamsBuilder();
        // source-topic의 데이터를 읽어와 KStream으로 변경
        KStream<String,String> sourceStream = builder.stream("source-topic");

        // 데이터변환 후
        // sink-topic으로 처리가 완료된 데이터 전송
        KStream<String,String> filterStream = sourceStream.filter((key, value) -> Integer.parseInt(value) % 2 ==0);
        filterStream.to("sink-topic");

        // 스트림즈 작업을 시작.
        KafkaStreams streams = new KafkaStreams(builder.build(),getProperties("stream-test1"));
        streams.start();
    }

    @Bean
    public void stream2(){

        StreamsBuilder builder = new StreamsBuilder();
        // source-topic의 데이터를 읽어와 KStream으로 변경
        KStream<String,String> sourceStream = builder.stream("topicA");

        // 데이터변환 후
        // sink-topic으로 처리가 완료된 데이터 전송
        // 입력된 글자 수가 4글자 미만 일 경우 싱크로

        KStream<String,String> filterStream = sourceStream.filter((key, value) -> value.length() < 4);
        filterStream.to("sink-topic");

        // 스트림즈 작업을 시작.
        KafkaStreams streams = new KafkaStreams(builder.build(),getProperties("stream-test1"));
        streams.start();
    }
}
