package com.example.streamsink.controller;

import com.example.streamsink.service.producer.MyProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class basicController {

    private final MyProducer producer;

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
