package com.example.productservice.dao;

import com.example.productservice.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductDAO {
    void write(Product product);  //Product가 Entity
    void insertAll(List<Product> prdlist);
    Product findByProductNo(Long productNo );
    List<Product> findAll();

    // 상품수량을 업데이트
    void update(Map<Object,Object> product);
}
