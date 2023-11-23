package com.example.kafkastream.controller;


import com.example.kafkastream.service.Producer;
import com.example.kafkastream.service.StreamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class basicController {
    private final StreamService streamService;
    private final Producer producer;


    @GetMapping("/home")
    private String test(){
        return "index";
    }

    @PostMapping("/home")
    private String test2(String val){
        log.info("값이 잘 찍혔나?" + val);
        producer.async("topicA",val);
        return "index";
    }

}
