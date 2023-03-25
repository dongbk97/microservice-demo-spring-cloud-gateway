package com.example.gatewayservice.config;

import com.example.gatewayservice.jwt.JwtEntryPointCs;
import com.example.gatewayservice.jwt.JwtFilterCus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilterCus jwtFilterCus;
    @Autowired
    private JwtEntryPointCs jwtEntryPointCs;
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPointCs)
                .and()
                .addFilterBefore(jwtFilterCus, SecurityWebFiltersOrder.AUTHORIZATION);
//                .authorizeExchange()
//                .pathMatchers("/**").permitAll()
//                .anyExchange().authenticated();

        return http.build();
    }


}
