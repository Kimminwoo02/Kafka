package com.example.customerservice.customer.dao;

import com.example.customerservice.customer.model.CustomerEntity;
import com.example.customerservice.customer.model.CustomerRequestDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

// private final이 추가된 멤버를 이용해서 생성자를 만들고 자동으로
//injection
@Repository
@RequiredArgsConstructor
public class CustomerDAOImpl implements CustomerDAO {
    private final CustomerRepository customerRepository;
    @Override
    public void write(CustomerEntity entity) {

        customerRepository.save(entity);
    }

    @Override
    public CustomerEntity login(String username) {
        return customerRepository.findByUsername(username);
    }

}
