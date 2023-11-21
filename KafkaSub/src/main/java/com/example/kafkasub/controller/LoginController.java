package com.example.kafkasub.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/login")
    public String main2(){
        return "login";
    }
    @GetMapping("/logout")
    public String main3(){
//        consumerDefault.stop();
        return "main";
    }
}
