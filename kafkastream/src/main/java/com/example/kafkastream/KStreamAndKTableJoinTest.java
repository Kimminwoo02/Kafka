package com.example.kafkastream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;

import java.util.Properties;

public class KStreamAndKTableJoinTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "jointest");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.5:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        // 조인을 하는 경우 두 개의 토픽이 파티션 갯수가 동일해야 한다.
        // order -- KStream
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> order = builder.stream("order");

        // customer - KTable
        KTable<String, String> customerSTream = builder.table("customer");

        // 조인
        // 값이 두 개가 전달된다. order, customer의 값이 각각 전달
        // result
        order.join(customerSTream,(orderValue,customerValue)-> orderValue+": " + customerValue ).to("result");

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

    }
}
