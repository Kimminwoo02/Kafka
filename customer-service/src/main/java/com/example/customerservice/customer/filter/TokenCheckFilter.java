package com.example.customerservice.customer.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.customerservice.customer.dao.CustomerRepository;
import com.example.customerservice.customer.model.CustomerEntity;
import com.example.customerservice.customer.model.CustomerUserDetail;
import com.example.customerservice.customer.service.CustomerDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TokenCheckFilter extends BasicAuthenticationFilter {
    private final CustomerDetailService detailService;
    public TokenCheckFilter(AuthenticationManager authenticationManager,
                            CustomerDetailService detailService) {
        super(authenticationManager);
        this.detailService = detailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException,
            ServletException {
        log.info("요청할때 실행되는 필터");
        //                                       -------------------
        String jwtHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("jwtHeader={}",jwtHeader);
        //    사용자정보를 조회해서 Authentication(UsernamePasswordAuthenticationToken)에 담고 SecurityContext에 공유
        if(jwtHeader==null || !jwtHeader.startsWith("Bearer ")){
            chain.doFilter(request,response);
            return;
        }
        //JWT토큰을 검증해서 정상사용자인지 확인 - JWT토큰을 파싱해서 같은 인증 토큰인지 확인
        String token = request.getHeader(HttpHeaders.AUTHORIZATION)
                .replace("Bearer ","");
        String username = JWT.require(Algorithm.HMAC256("msa")).build()
                                    .verify(token)
                                    .getClaim("username").asString();
        log.info("토큰에서 꺼낸 username=>{}",username);
        //수정해야 하는 부분 => 빠르게 테스트하기 위해서 작업
        //인증된 사용자면 사용자 정보를 가져오거나 필요한 작업을 수행
        if(username!=null){
            CustomerUserDetail userDetail =
                   (CustomerUserDetail) detailService.loadUserByUsername(username);
            Authentication securityToken =
                    new UsernamePasswordAuthenticationToken(userDetail,
                            null,userDetail.getAuthorities());
            //시큐리티내부에서 사용되는 public저장소
            SecurityContextHolder.getContext().setAuthentication(securityToken);
            log.info("시큐리티인증토큰=>{}",securityToken);

        }
        chain.doFilter(request,response);
        //super.doFilterInternal(request, response, chain);
    }
}











