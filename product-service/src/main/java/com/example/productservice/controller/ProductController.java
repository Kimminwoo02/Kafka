package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.service.CommunicationService;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final CommunicationService comm_service;
    @GetMapping("/getdata")
    public String getData(){

        service.insertAll(comm_service.getProductList());
        return  "ok";

    }
}
