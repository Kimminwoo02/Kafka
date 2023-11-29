//package com.example.kafkastream.config;
//
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KeyValue;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.kstream.KStream;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafkaStreams;
//import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
//import org.springframework.kafka.config.KafkaStreamsConfiguration;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//@Configuration
//@EnableKafkaStreams
//// 스프링이 기본으로 제공하는 스트림빌더를 사용할 수 있도록
//public class KafkaStreamsConfig {
//
////    @Bean
//    public KStream<String, String> makeString (StreamsBuilder builder){
//
//        // 스트림을 구독하고 재발행 하기위해 생성
//        KStream<String, String> stream = builder.stream("streamstest2");
//
//
//        // 리턴값 없이 출력하기 위한 용도
//         stream.peek(((key, value) -> {
//             System.out.println("key => "+ key+" value =>"+ value);
//         }))
//                 .map((key, value) -> KeyValue.pair(key,value+"============> result "))
//                 .to("streams-result");
//         return stream;
//    }
//
//    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//    public KafkaStreamsConfiguration streamConfig(){
//        Map<String, Object> config =  new HashMap<>();
//        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.0.5:9092");
//        config.put(StreamsConfig.APPLICATION_ID_CONFIG,"stream-test-1-2"); // application id는 컨슈머 그룹 아이디를 대체
//        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//
//        // 한번 만 처리되는 것을 보장하기 위해. 재시작하면서 Commit 되지 않은 값이 있는 경우 재구독을 시도하므로 테스트 하는 동안
////        config.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);
//        return new KafkaStreamsConfiguration(config);
//    }
//}
