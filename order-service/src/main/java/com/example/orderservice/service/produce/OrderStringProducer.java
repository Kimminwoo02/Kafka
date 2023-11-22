package com.example.orderservice.service.produce;

import com.example.orderservice.domain.OrderDetailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderStringProducer {
    // 물건이 주문될 때 메세지 전송
    private final KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String topic , OrderDetailDTO orderInfo)  {
        // 메세지 전송 ->   OrderDetailDTO ==> String으로 바뀌어야 한다.
        ObjectMapper mapper = new ObjectMapper();
        String orderStr = "";
        try {
            orderStr = mapper.writeValueAsString(orderInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // publish
//        kafkaTemplate.send(topic,orderStr);
        log.info("#################");
        log.info("프로듀서가 데이터 전송", orderStr);
        log.info("#################");
    }


}
