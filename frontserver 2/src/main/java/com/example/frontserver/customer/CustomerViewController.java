package com.example.frontserver.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerViewController {
    @GetMapping("/create")
    public String create(){
        return "customer/register";
    }
    @GetMapping("/login")
    public String login(){
        return "customer/loginForm";
    }
}
