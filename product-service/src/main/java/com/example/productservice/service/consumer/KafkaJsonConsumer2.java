package com.example.productservice.service.consumer;

import com.example.productservice.dao.ProductDAO;
import com.example.productservice.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaJsonConsumer2 {
    // 브로커로부터 메시지를 받으면 DB에 데이터를 수정(재고 수정)
    private final ProductDAO dao;

    @KafkaListener(topics = "ordercreate", groupId = "updatestockgroup",containerFactory="getKafkaListenerContainerFactory")
    public void receiveMsg(String message){

        log.info("전달받은 메시지 {}",message);
        Map<Object,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
            log.info("전달받은 메시지 ==> {}", message);
            dao.update(map);
            // 메시지 받은 후에 상품재고 수정
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
