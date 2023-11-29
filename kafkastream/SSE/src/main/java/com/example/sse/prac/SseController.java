package com.example.kafkastream.SSE;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping("/sse")
@RequiredArgsConstructor
public class SseController {

    // 서비스 객체가 서버에 SseEmitter 객체를 저장할 수 있는 메소드를 제공
    private final SseEmitterService service;

    // 클라이언트가 구독신청을 하는 경우 호출되는 메소드

    @CrossOrigin
    @GetMapping(value = "/connect",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(){

        // SseEmitter 객체는 나중에 이벤트가 발생하면
        // 해당 클라이언트로 이벤트를 전송하기 위해서 사용하기 때문에 서버가 저장하고 있어야 한다.

        SseEmitter emitter = new SseEmitter();
        service.add(emitter);

        //1. 생성자를 이용해서 만료시간을 설정할 수 있다. (디폴트값은 서버에 따라 다르다.)
        //  => 만료시간을 설정하지 않는 경우 스프링부트 내장 톰캣을 사용하면 30초로 설정
        //  => 만료시간이 되면 브라우저에서 자동으로 서버에 재연결을 요청한다.

        //2. SseEmitter를 생성하고 만료시간까지 아무런 데이터를 보내지 않으면
        // 재연결을 요청할 때 503번 응답오류가 발생할 수 있다.
        // Sse 최초 연결시에 연결이 완료됐다는 더미 데이터(테스트)를 클라이언트에 전송
        // 발생한 이벤트, 이벤트이름과 클라이언트에 전송할 메세지...

        // 3. 이벤트 이름을 정의하면 클라이언트에서 이 이벤트별로 메세지에 대한 처리를 이벤트별로 작업할 수 있다.

        try {
            emitter.send(SseEmitter.event()
                    .name("connect") // 이벤트이름을 connect라는 이름으로 정의
                    .data("connected!!")); // 503번 에러를 방지하기 위해서 더미데이터 전송

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(emitter);

    }

    // 설정을 변경하면 같이 연결되어 있는 모든 클라이언트에 변경된 설정을 전송할 수 있도록 작업
    @GetMapping("/change")
    public ResponseEntity<Void> change(){

        // 값을 변경하고 클라이언트에 전송할 수 있도록 처리
        service.count1();
        return ResponseEntity.ok().build();


    }




}
