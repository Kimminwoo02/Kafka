package com.example.communicationserver.restTemplate.controller;

import com.example.communicationserver.restTemplate.model.SampleRequest;
import com.example.communicationserver.restTemplate.model.SampleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

//서비스를 제공하는 컨트롤러
@RestController
@RequestMapping("/api/server")
@Slf4j
public class RestServerController {
    @GetMapping("/first")
    public String firstRequest(){
        return "first communication";
    }
    @GetMapping("/getdata")
    public SampleResponse getdata(){
        //DBMS에서 조회한 데이터를 응답
        SampleResponse responsedto =
                new SampleResponse(1L,"bts","1234","방탄소년단");
        return responsedto;
    }
    @GetMapping("/paramtest")
    public SampleResponse paramtest(String username,String password,String name){
        //DBMS에서 조회한 데이터를 응답
        SampleResponse responsedto =
                new SampleResponse(1L,username,password,name);
        return responsedto;
    }
    @GetMapping("/board/{username}/{id}")
    public String pathvariable(@PathVariable String username, @PathVariable String id){
        log.info("username={}",username);
        log.info("id={}",id);
        return "확인완료";
    }
    @PostMapping("/create")
    public SampleRequest create(@RequestBody SampleRequest dto){
        log.info("post로 전송받은 dto=>{}",dto);
        return dto;
    }

    @PostMapping("/exchange/test")
    public SampleRequest exchangetest(@RequestBody SampleRequest dto,
                                      @RequestHeader("authorization") String authorization,
                                      @RequestHeader("mytoken")String token){
        log.info("post로 전송받은 헤더1=>{}",authorization);
        log.info("post로 전송받은 헤더2=>{}",token);

        return dto;
    }
}













