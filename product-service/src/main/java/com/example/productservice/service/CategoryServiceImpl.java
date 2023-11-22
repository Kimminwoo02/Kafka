package com.example.productservice.service;

import com.example.productservice.dao.CategoryDAO;
import com.example.productservice.dto.CategoryRequest;
import com.example.productservice.entity.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryDAO dao;
    @Override
    public void write(CategoryRequest category) {
        //CategoryRequest -> Entity
        ModelMapper mapper = new ModelMapper();
        Category categoryEntity = mapper.map(category,Category.class);
        dao.write(categoryEntity);
    }
}
