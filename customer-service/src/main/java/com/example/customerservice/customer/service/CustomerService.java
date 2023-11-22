package com.example.customerservice.customer.service;



import com.example.customerservice.customer.model.CustomerOrderResponseDTO;
import com.example.customerservice.customer.model.CustomerRequestDTO;
import com.example.customerservice.customer.model.OrderResponseDTO;

import java.util.List;

public interface CustomerService {
    void write(CustomerRequestDTO comment);
    //RestTemplate을 이용해서 작업
    CustomerOrderResponseDTO getOrdersByCustomerId(Long customerId);
    CustomerOrderResponseDTO getOrdersByCustomerId_Feign(Long customerId);
}

