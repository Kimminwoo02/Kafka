package com.example.customerservice.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class FilterConfig {
    @Bean
    public CorsFilter corsFilter(){
        //허용할 수 있는 부분을 정의
        //정의한 자원의 허용범위값을 config에 셋팅
        //config를 어떤 요청 url에 적용할 것인지 UrlBasedCorsConfigurationSource에 설정
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        //json서버응답을 자바스크립트에서 처리할 수 있도록 허용
        //크로스도메인에서 요청주고 받을때 쿠키에 대한 처리, Authorization이 있는 요청 모두 허용
        //클라이언트에서도 처리
        config.setAllowCredentials(true);
        //버전이 달라지면서 사용메소드가 변경
        //config.addAllowedOrigin("*"); //모든 ip에 대해서 응답을 허용하겠다는 의미
        config.addAllowedOriginPattern("*");
        config.addAllowedMethod("*"); //모든 http method를 허용
        config.addAllowedHeader("*"); //모든 http header를 허용
        //외부에서 헤더값을 읽기 위한 설정
        config.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }
}
