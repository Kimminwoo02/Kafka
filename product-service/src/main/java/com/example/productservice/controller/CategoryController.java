package com.example.productservice.controller;

import com.example.productservice.dto.CategoryRequest;
import com.example.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;
    @PostMapping("/category/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest requestdto){
        service.write(requestdto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
