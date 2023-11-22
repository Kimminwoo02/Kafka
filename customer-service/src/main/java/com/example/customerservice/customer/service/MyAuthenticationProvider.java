package com.example.customerservice.customer.service;

import com.example.customerservice.customer.model.CustomerUserDetail;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {
    private final  CustomerDetailService customerDetailService;
    private final  PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();//username추출
        String password = (String) authentication.getCredentials();
        CustomerUserDetail userDetail =
                (CustomerUserDetail)customerDetailService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = null;
        boolean state = false;
        if (userDetail != null) {
            state = passwordEncoder.matches(password,userDetail.getPassword());
            //ModelMapper mapper = new ModelMapper();
            //SampleDTO2 dto = mapper.map(userDetail.getEntity(),SampleDTO2.class);
            if(state){ //인증성공
                token =  new UsernamePasswordAuthenticationToken(userDetail.getCustomerResponseDTO(),
                        null,
                        userDetail.getAuthorities());

            }
        }
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

