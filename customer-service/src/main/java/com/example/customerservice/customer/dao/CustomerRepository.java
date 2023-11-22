package com.example.customerservice.customer.dao;


import com.example.customerservice.customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<CustomerEntity,Long> {
    //아이디정보로 유저를 조회하기
    CustomerEntity findByUsername(String username);
}
