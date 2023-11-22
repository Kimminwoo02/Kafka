package com.example.customerservice.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponseDTO {
    private Long orderDetailId;
    private Long productNo;
    private int orderPrice;//구매가격
    private int count;//주문수량
}
