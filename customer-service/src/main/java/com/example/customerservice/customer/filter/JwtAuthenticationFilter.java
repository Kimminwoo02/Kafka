package com.example.customerservice.customer.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.customerservice.customer.model.CustomerOrderResponseDTO;
import com.example.customerservice.customer.model.CustomerResponseDTO;
import com.example.customerservice.customer.model.CustomerUserDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    //인증요청(로그인 : /login)을 하면 실행될 메소드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
                throws AuthenticationException {
        //1.username, password추출
//        try {
//            BufferedReader br =  request.getReader();
//            String line="";
//            while((line = br.readLine())!=null){
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        ObjectMapper mapper = new ObjectMapper();
        CustomerResponseDTO requestdto = null;
        try {
            requestdto = mapper.readValue(request.getInputStream(),
                    CustomerResponseDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("변환된 dto ==========>{}",requestdto);
        log.info("========================================================");
        log.info("인증요청을 받으면 자동으로 실행되는 메소드");
        log.info("========================================================");
        //1. 인증하면서 전달한 username가 password를 이
        // .용해서 UsernamePasswordAuthenticationToken을 만들기
        UsernamePasswordAuthenticationToken securitytoken =
                new UsernamePasswordAuthenticationToken(requestdto.getUsername(),requestdto.getPassword());
        log.info(("1.유저가 입력한 값으로 토큰생성완료================================"));
//        2. authenticationManager.authenticate()메소드를 호출하면서 Authentication객체 전달
        Authentication resultToken = authenticationManager.authenticate(securitytoken);
        // 3. 호출 후 전달되는 객체를 출력
        CustomerUserDetail data = (CustomerUserDetail) resultToken.getPrincipal();

        log.info("2. 인증 후 토큰객체를 가지고 돌아옴 ===>{}"
                            ,data.getCustomerResponseDTO());
        //4. return 검증결과로 받은 token을 리턴
        return resultToken;
    }
    //인증성공하면 실행되는 메소드
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //인증이 성공했으므로 토큰을 발급
        //response에 셋팅
        CustomerUserDetail principal = (CustomerUserDetail) authResult.getPrincipal();
        log.info("------------------로그인인증 성공---------------------------------------");
        // JWT 토큰만들기
        String auth_token = JWT.create()
                .withSubject("web")
                .withClaim("username",principal.getCustomerResponseDTO().getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()
                        +1000*60*100))
                .sign(Algorithm.HMAC256("msa"));
        response.addHeader(HttpHeaders.AUTHORIZATION,"Bearer "+auth_token);

    }
}
