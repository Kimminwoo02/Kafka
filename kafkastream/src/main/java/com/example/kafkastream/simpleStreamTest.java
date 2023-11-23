package com.example.kafkastream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class simpleStreamTest {

    public static Properties getProperties(String app_id){
        //기본설정에 대한 작업
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, app_id);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.5:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return props;
    }
    public static void streams1(){
        //카프카 스트림즈를 만드는 작업을 위한 API추가
        StreamsBuilder builder = new StreamsBuilder();
        //source-topic의 데이터를 읽어와 KStream으로 변경
        KStream<String,String> sourceStream = builder.stream("source-topic");
        //데이터 변환
        KStream<String,String> filterStream = sourceStream.filter((key, value) -> Integer.parseInt(value) % 2 != 0);
        //sink-topic으로 처리가 완료된 데이터 전송
        filterStream.to("sink-topic");
        //스트림즈 작업을 시작
        KafkaStreams streams = new KafkaStreams(builder.build(), getProperties("stream-test1"));
        streams.start();


    }
    public static void streams2(){
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String,String> sourceStream = builder.stream("topicA");
        KStream<String,String> filterStream = sourceStream.filter((key, value) -> value.length()<=3);
        filterStream.to("sink-topic");
        KafkaStreams streams = new KafkaStreams(builder.build(), getProperties("stream-test2"));
        streams.start();
    }
    public static void main(String[] args) {
        streams1();
//        streams2();
    }
}
