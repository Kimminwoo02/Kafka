package com.example.customerservice.customer.security.handler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.customerservice.customer.model.CustomerOrderResponseDTO;
import com.example.customerservice.customer.model.CustomerResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Component
public class MySuccessHandler implements AuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy  = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        System.out.println(authentication.getPrincipal());
        //인증이 성공하면 토큰에서 공유된 객체를 꺼내서 세션에 저장하기
        CustomerResponseDTO dto = (CustomerResponseDTO) authentication.getPrincipal();
        request.getSession().setAttribute("user",dto);
        // 토큰만들기
        String auth_token = JWT.create()
                .withSubject("web")
                .withClaim("customerGenerateId", dto.getCustomerGenerateId())
                .withExpiresAt(new Date(System.currentTimeMillis()
                        +1000*60*60*60*24))
                .sign(Algorithm.HMAC256("msa"));
        response.addHeader("Authorization","Bearer "+auth_token);

        SavedRequest savedRequest =  requestCache.getRequest(request,response);
        //인증 후에 사용자가 인증 전에 방문한 사이트로 바로 이동하거나 첫 페이지로 이동
        if(savedRequest!=null){
            String url = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request,response,url);
        }else{
            redirectStrategy.sendRedirect(request,response,"/");
        }


    }
}
