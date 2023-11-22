package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;

import java.util.List;

public interface CommunicationService {
    List<ProductRequest> getProductList();
}
