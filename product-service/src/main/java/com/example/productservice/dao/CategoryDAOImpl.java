package com.example.productservice.dao;

import com.example.productservice.entity.Category;
import com.example.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryDAOImpl implements CategoryDAO{
    private final CategoryRepository repository;
    @Override
    public void write(Category category) {
        repository.save(category);
    }

    @Override
    public Category findByCategoryId(Long categoryId) {
        return repository.findById(categoryId).get();
    }
}
