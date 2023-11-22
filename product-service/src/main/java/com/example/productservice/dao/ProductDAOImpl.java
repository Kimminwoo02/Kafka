package com.example.productservice.dao;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{
    private final ProductRepository repository;
    @Override
    public void write(Product product) {

    }

    @Override
    public void insertAll(List<Product> prdlist) {
        repository.saveAll(prdlist);
    }

    @Override
    public Product findByProductNo(Long productNo) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void update(Map<Object, Object> product) {

        Integer key = (Integer) product.get("productNo");
        // 조회하기
        Product entity = repository.findById((long) key).get();
        if(entity != null){
            // 재고 수정
            entity.setStock(entity.getStock() - (Integer) product.get("count"));
            repository.save(entity);
        }
    }
}
