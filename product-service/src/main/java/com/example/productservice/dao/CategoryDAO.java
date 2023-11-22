package com.example.productservice.dao;

import com.example.productservice.entity.Category;

public interface CategoryDAO {
    void write(Category category);
    Category findByCategoryId(Long categoryId);
}
