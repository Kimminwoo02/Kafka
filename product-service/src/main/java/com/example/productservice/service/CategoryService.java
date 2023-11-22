package com.example.productservice.service;

import com.example.productservice.dto.CategoryRequest;
import com.example.productservice.entity.Category;

public interface CategoryService {
    void write(CategoryRequest category);
}
