package com.example.kafkastream.SSE.controller;

import com.example.kafkastream.SSE.SseEmitterService;
import com.example.kafkastream.SSE.service.SseProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/sse")
public class SseProductController {

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 3;

    private final SseProductService service;

    @GetMapping(value = "/connect/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@PathVariable Long id){

        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        service.add(id,emitter);
        try {
            emitter.send(SseEmitter.event()
                    .name("prd") // 이벤트이름을 connect라는 이름으로 정의
                    .data("connected!!")); // 503번 에러를 방지하기 위해서 더미데이터 전송

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(emitter);

    }
}
