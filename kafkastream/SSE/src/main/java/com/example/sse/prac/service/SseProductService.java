package com.example.kafkastream.SSE.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class SseProductService {
    private final Map<Long,SseEmitter> emitterMap = new ConcurrentHashMap<>();


    public void add(Long id, SseEmitter emitter){
        emitterMap.put(id,emitter);
        emitter.onCompletion(()->{

            log.info("==========onCompletion");
            this.emitterMap.remove(emitter);

        });

        emitter.onTimeout(()->{
            log.info("============== onTimeOut");
            emitter.complete();

        });
    }




    // 아이디에 해당하는 Emitter삭제
    public void deleteById(Long id){
        log.info("+=================== deleteById ============{}",id);
        emitterMap.remove(id);
    }

    // id키로 저장된 Emitter 꺼내기

    public SseEmitter get(Long id){
        return emitterMap.get(id);
    }

    // 조건에 맞는 클라이언트에게 메시지 보내기
    // 현재는 구독신청하면 아이디와 함께 무조건 EmitterMap에 저장되는 구조
    // 추후에는 아이디에 해당하는 Emitter만 꺼내서 메세지 전송할 필요가 있다.
    //
    public void snedToClient(Object data){
        // map에 저장된 모든 클라이언트에게 알림을 전송
        emitterMap.forEach((aLong, emitter) -> {
            log.info("id===============" + aLong);
            log.info("id===============" + emitter);
            SseEmitter clientEmitter = get(aLong);
            if(clientEmitter != null ){
                try {
                    clientEmitter.send(SseEmitter.event()
                                    .id(String.valueOf(aLong))
                                    .name("prd")
                                    .data(data));
                } catch (IOException e) {
                    System.out.println("+==============");
                    deleteById(aLong);
                    emitter.completeWithError(e);
                }

            }
        });
    }



}
