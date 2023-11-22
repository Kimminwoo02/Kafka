package com.example.productservice.service;

import com.example.productservice.dao.CategoryDAO;
import com.example.productservice.dao.ProductDAO;
import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private  final ProductDAO dao;
    private  final CategoryDAO categoryDAO;
    @Override
    public void write(ProductRequest product) {

    }

    @Override
    public ProductResponse findByProductNo(Long productNo) {
        return null;
    }

    @Override
    public List<ProductResponse> findAll() {
        return null;
    }

    @Override
    @Transactional
    public void insertAll(List<ProductRequest> prdlist) {
        ModelMapper mapper = new ModelMapper();
        //카테고리정보 조회하기
        Category category = categoryDAO.findByCategoryId(1L);
        //DTO -> 엔티티로 변환
        List<Product> list = prdlist.stream()
                .map((item) ->{
                    item.setCategory(category);
                    return mapper.map(item,Product.class);
                }).collect(Collectors.toList());
        System.out.println(list);
        dao.insertAll(list);
    }
}














