package com.example.customerservice.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
//DB에서 조회한 주문정보를 담을 DTO => 클라이언트로 전송(JSON으로 변환되어)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private String addr; //배송주소
    private Long customerId;
    private Date orderDate;
    private List<OrderDetailResponseDTO> orderproductlist;
}
