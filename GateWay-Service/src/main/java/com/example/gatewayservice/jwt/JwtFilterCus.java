package com.example.gatewayservice.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class JwtFilterCus implements WebFilter {
    @Autowired
    private JwtUtils jwtUtils;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String authHeader = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION_HEADER);

        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            // Lấy token từ header
            String token = authHeader.substring(BEARER_PREFIX.length());

            // Validate token

            log.error("Token is invalid: " + jwtUtils.validateJwtToken(token));

            if (jwtUtils.validateJwtToken(token)) {
                // Token hợp lệ, lưu thông tin người dùng vào SecurityContext
                Authentication auth = new JwtAuthenticationToken(jwtUtils.getUserFromToken(token));
                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
            }
        }

        // Nếu token không hợp lệ hoặc không có token, trả về null để chặn request

        return null;
    }
}


