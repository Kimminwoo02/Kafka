package com.example.kafkatest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Resilience4jConfiguration{
//    @Bean
//    public Customizer<Resilince4JCircuitBreakerFactory> globalCustomConfiguration(){
//
//        CircuitBreakerConfig circuitBreakerConfig =CircuitBreakerConfig.custom()
//                .failureRateThreshold(4) // CircuitBReaker를 오픈할지 말지 결정하는 Failure rate, default : 50
//                .waitDurationInOpenState(Duration.ofMillis(1000)) //
//                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
//                .slidingWindowSize(2)
//                .build();
//
//    }


}