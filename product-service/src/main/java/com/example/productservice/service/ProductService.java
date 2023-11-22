package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    void write(ProductRequest product);
    ProductResponse findByProductNo(Long productNo );
    List<ProductResponse> findAll();
    void insertAll(List<ProductRequest> prdlist);
}
