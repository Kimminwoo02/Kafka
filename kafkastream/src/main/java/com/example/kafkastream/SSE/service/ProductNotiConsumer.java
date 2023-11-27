package com.example.kafkastream.SSE.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductNotiConsumer {
    private final SseProductService service;
    @KafkaListener(topics = "prdcreate", id="pro_noti", containerFactory = "getKafkaListenerContainerFactory")
    public void listener(String message) {
        log.info("================");
        log.info(message);
        log.info("================");
        // 메세지가 create이면 구독 신청한 클라이언트에게 메세지 전송
        if(message.equals("create")){
            service.snedToClient(message);
        }


    }

}
