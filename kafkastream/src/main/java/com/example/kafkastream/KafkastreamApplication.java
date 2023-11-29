package com.example.kafkastream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkastreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkastreamApplication.class, args);
        String data1 ="msg";
        System.out.println(data1.hashCode());

        String data2 ="asdfasdf";

        System.out.println(data2.hashCode());
    }

}
