package com.example.customerservice.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerRequestDTO {
    private String username;
    private String password;
    private String name;
    private String addr;
    private String tel;
    private String info;
    private String role;
}
