package com.example.gatewayservice.config;

import com.example.gatewayservice.jwt.JwtFilterCus;
import com.example.gatewayservice.jwt.gatewayfilter.GateWayFilterCus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Autowired
    private GateWayFilterCus filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/auth/**").uri("http://localhost:9100/"))
                .route("product-service", r -> r.path("/product/**").filters(f -> f.filter(filter)).uri("http://localhost:8080/"))
                .route("cart-service", r -> r.path("/cart/**").filters(f -> f.filter(filter)).uri("http://localhost:8081/"))
                .build();
    }




}
