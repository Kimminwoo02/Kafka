package com.example.kafkaexam.controller;


import com.example.kafkaexam.service.ExamProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final ExamProducer producer;

    @GetMapping("/pub_page")
    public String test(){

        log.info("안녕하세요??!");
//        KafkaTemplate<String,String> kafkaTemplate = null;
//        KafkaMessageListenerContainer<String,String> container = null;
//        kafkaTemplate.send("springexam","im hojin");
        log.info("이곳으로 왔나??!");

        return "pub";
    }

    @GetMapping("/pub")
    public String pub(){
        System.out.println("");
        producer.sendMessage("kafkaexam2");
        return "pub";
    }

}
