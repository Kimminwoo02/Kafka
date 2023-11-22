package com.example.customerservice.customer.dao;


import com.example.customerservice.customer.model.CustomerEntity;
import com.example.customerservice.customer.model.CustomerRequestDTO;

import java.util.List;

public interface CustomerDAO {
    void write(CustomerEntity customerRequest);
    CustomerEntity login(String username);
}
