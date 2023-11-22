package com.example.customerservice.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerOrderResponseDTO {
    //마이페이지에서 사용할 customer정보와 주문내역정보를 담고 있는 dto
    //고객의 개인정보
    CustomerResponseDTO customerInfo;
    //고객의 주문정보
    List<OrderResponseDTO> orderlist;
}
