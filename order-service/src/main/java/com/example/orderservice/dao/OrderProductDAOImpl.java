package com.example.orderservice.dao;

import com.example.orderservice.domain.OrderProductEntity;
import com.example.orderservice.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderProductDAOImpl implements OrderProductDAO{
    private final OrderDetailRepository repository;

    @Override
    public void save(List<OrderProductEntity> orderproductlist) {
        repository.saveAll(orderproductlist);
    }
}
