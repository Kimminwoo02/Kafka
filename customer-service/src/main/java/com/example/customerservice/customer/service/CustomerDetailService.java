package com.example.customerservice.customer.service;

import com.example.customerservice.customer.dao.CustomerDAO;
import com.example.customerservice.customer.model.CustomerEntity;
import com.example.customerservice.customer.model.CustomerOrderResponseDTO;
import com.example.customerservice.customer.model.CustomerResponseDTO;
import com.example.customerservice.customer.model.CustomerUserDetail;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//인메모리방식에서 인증하던 작업을 DB기반으로 변경하기 위해서 커스트마이징
@Service
@RequiredArgsConstructor
public class CustomerDetailService implements UserDetailsService {
    private final CustomerDAO customerDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //customerDAO의 메소드를 호출해서 사용자가 있는지 조회
        CustomerEntity entity =  customerDAO.login(username);
        if(entity==null){
            throw  new UsernameNotFoundException("사용자없음");
        }
        //권한정보와 조회한 사용자객체를 이용해서 UserDetials객체를 생성해서 리턴
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(entity.getRole()));
        ModelMapper mapper = new ModelMapper();
        CustomerResponseDTO responseDTO = mapper.map(entity, CustomerResponseDTO.class);
        //서비스단에서 뷰로 넘기기 위해 Entity -> DTO변환
        //스프링시큐리티내부에서 인증처리를 할 수 있도록 하기 위해서 요구되는 타입으로 DTO를 한번 더 감싸준다.
        CustomerUserDetail userDetail = new CustomerUserDetail(roles,responseDTO);
        System.out.println(userDetail+"------------------------------------------------------------");
        return userDetail;
    }
}
