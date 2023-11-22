package com.example.customerservice.customer.controller;

import com.example.customerservice.customer.model.CustomerRequestDTO;
import com.example.customerservice.customer.model.CustomerOrderResponseDTO;
import com.example.customerservice.customer.model.CustomerResponseDTO;
import com.example.customerservice.customer.model.CustomerUserDetail;
import com.example.customerservice.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<?> createComment(CustomerRequestDTO request){
        System.out.println(request+"==============================");
        customerService.write(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/my/api/test")
    public String usertest(){
        return "user";
    }
    @GetMapping("/my/api/mypage")
    public CustomerOrderResponseDTO mypage(Authentication mydata){
        CustomerUserDetail userDetail = (CustomerUserDetail) mydata.getPrincipal();
        ModelMapper mapper = new ModelMapper();
        CustomerResponseDTO dto =userDetail.getCustomerResponseDTO();
        //order-service에서 구매내역 조회하기
        //고객의 일반적인 내용과 주문내역을 함께 담고 있는 dto
        CustomerOrderResponseDTO customerdto
                = customerService.getOrdersByCustomerId_Feign(dto.getCustomerGenerateId());
        return customerdto;
    }
    @GetMapping("/admin/api/test")
    public String admintest(){
        return "admin";
    }
}
