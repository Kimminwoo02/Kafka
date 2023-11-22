package com.example.customerservice.customer.service;

import com.example.customerservice.customer.dao.CustomerDAO;
import com.example.customerservice.customer.model.*;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final PasswordEncoder passwordEncoder;
    private final CustomerDAO dao;
    private final RestTemplate restTemplate;
    private final FeignClientService service;
    @Transactional
    @Override
    public void write(CustomerRequestDTO customer) {
       ModelMapper mapper = new ModelMapper();
       CustomerEntity entity = mapper.map(customer,CustomerEntity.class);
        System.out.println("====================>"+entity);
       entity.setPassword(passwordEncoder.encode(entity.getPassword()));
       dao.write(entity);

    }
    //로그인한 사용자의 주문정보 order-service에 요청해서 리턴하는 메소드
    @Override
    public CustomerOrderResponseDTO getOrdersByCustomerId(Long customerId) {
        //커스터머의 기본정보조회 -시큐리티의 범용저장소에 저장된 인증된 회원의 정보를 꺼내기
        //세션에 공유된 객체 꺼내는 것과 동일한 작업
        CustomerUserDetail detail =
                (CustomerUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //커스터머의 주문내역조회
        String url = "http://192.168.0.114:9500/order/getOrders/"+customerId;
        ResponseEntity<List<OrderResponseDTO>> orderlistResponse =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<OrderResponseDTO>>() {
                        });
        List<OrderResponseDTO> orderlist = orderlistResponse.getBody();
        log.info("=====================================================");
        log.info("주문내역결과=>{}",orderlist);
        log.info("=====================================================");


        //고객데이터와 주문데이터를 dto로 만들어서 리턴
        CustomerOrderResponseDTO customer_order_dto = new CustomerOrderResponseDTO();
        customer_order_dto.setCustomerInfo(detail.getCustomerResponseDTO());
        customer_order_dto.setOrderlist(orderlist);
        return customer_order_dto;
    }

    @Override
    public CustomerOrderResponseDTO getOrdersByCustomerId_Feign(Long customerId) {
        //고객의 일반적인 정보
        CustomerUserDetail detail =
                (CustomerUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerOrderResponseDTO customer_order_dto = new CustomerOrderResponseDTO();
        customer_order_dto.setCustomerInfo(detail.getCustomerResponseDTO());
        //통신하기
        List<OrderResponseDTO> orders = null;
        try{
            orders = service.getOrders(customerId);
        }catch(FeignException e){

        }
        customer_order_dto.setOrderlist(orders);
        return customer_order_dto   ;
    }

}
