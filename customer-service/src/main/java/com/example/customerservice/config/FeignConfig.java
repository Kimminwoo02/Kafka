package com.example.customerservice.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@EnableFeignClients(basePackages = "com.example.customerservice")
public class FeignConfig {
}
