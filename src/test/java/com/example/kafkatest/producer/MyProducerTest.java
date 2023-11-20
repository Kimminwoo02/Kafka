package com.example.kafkatest.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MyProducerTest {
    @Autowired
    private MyProducer producer;
    @Test
    void test(){
        producer.send();
    }

}