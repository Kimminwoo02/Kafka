package com.example.customerservice.config;

import com.example.customerservice.customer.dao.CustomerRepository;
import com.example.customerservice.customer.filter.JwtAuthenticationFilter;
import com.example.customerservice.customer.filter.TokenCheckFilter;
import com.example.customerservice.customer.service.CustomerDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class TokenSecurityConfig {
    private final CustomerDetailService customerSecurityDetailService;
    private final CorsFilter corsFilter;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerSecurityDetailService);
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(){
        AuthenticationManager manager = new ProviderManager(authenticationProvider());
        return manager;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session ->{
            //세션을 사용하지 않겠다는 정의
             session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            } )
            .addFilter(corsFilter)
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new TokenCheckFilter(authenticationManager(),customerSecurityDetailService))
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"**").permitAll()
                .antMatchers("/customer/my/api/**")
                    .access("hasRole('USER') or  hasRole('ADMIN')")
                .antMatchers("/customer/admin/api/**")
                    .access("hasRole('ADMIN')")
                .anyRequest().permitAll()
            .and().cors()
            ;
        return http.build();
    }

}










