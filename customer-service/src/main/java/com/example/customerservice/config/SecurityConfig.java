package com.example.customerservice.config;

import com.example.customerservice.customer.security.handler.MyFailureHandler;
import com.example.customerservice.customer.security.handler.MySuccessHandler;
import com.example.customerservice.customer.service.CustomerDetailService;
import com.example.customerservice.customer.service.MyAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableWebSecurity(debug = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@RequiredArgsConstructor
public class SecurityConfig {
    private  CustomerDetailService customerSecurityDetailService;
    private  MySuccessHandler successHandler;
    private  MyFailureHandler failureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(request -> {
            request.antMatchers("/","/customer/create").permitAll()
                    .anyRequest().authenticated();
            })
            .formLogin(login -> {
                login.loginPage("/customer/login").permitAll()
                        .defaultSuccessUrl("/",false)
                        .successHandler(successHandler)
                        .failureHandler(failureHandler);
            }).logout(logout -> logout.logoutSuccessUrl("/") )
            .csrf().disable();
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        return  new MyAuthenticationProvider(customerSecurityDetailService,
                            passwordEncoder());
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> {
            web.ignoring()
                    .requestMatchers(
                            PathRequest.toStaticResources().atCommonLocations()
                    ) ;
        };
    }
}











