package com.example.customerservice.customer.service;

import com.example.customerservice.customer.model.OrderResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "order", url = "http://127.0.0.1:9500")
public interface FeignClientService {
    @GetMapping("/order/getOrder/{customerId}")
    List<OrderResponseDTO> getOrders(@PathVariable("customerId") Long customerId);

}
