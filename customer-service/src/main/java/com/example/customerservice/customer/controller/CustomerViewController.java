package com.example.customerservice.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerViewController {
    @GetMapping("/mypage")
    public String mypage(){
        return "customer/mypage";
    }
    @GetMapping("/create")
    public String create(){
        return "customer/register";
    }
    @GetMapping("/login")
    public String login(){
        return "customer/loginForm";
    }
}
