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
public class CustomerResponseDTO {
    //마이페이지에서 사용할 customer정보와 주문내역정보를 담고 있는 dto
    private Long customerGenerateId;
    private String username;
    private String password;
    private String name;
    private String addr;
    private String tel;
    private Date writeDate;
    private Date modifyDate;
    private int point;
    private String info;
    private String role;

}
