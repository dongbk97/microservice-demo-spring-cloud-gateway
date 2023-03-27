package com.example.gatewayservice.jwt;

import com.example.gatewayservice.exceptionhandle.JwtTokenMalformedException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


//@Component
//@Slf4j
public class JwtFilterCus /*implements WebFilter*/ {
//    @Autowired
//    private JwtUtils jwtUtil;
//
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//    private static final String BEARER_PREFIX = "Bearer ";
//
//
//
////    @Override
////    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
////        ServerHttpRequest request = exchange.getRequest();
////
////
////            if (this.isAuthMissing(request))
////                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
////
////            final String token = this.getAuthHeader(request);
////
////            if (jwtUtil.validateJwtToken(token))
////                return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
////
//////            this.populateRequestWithHeaders(exchange, token);
////
////        return chain.filter(exchange);
////    }
//
//
//    /*PRIVATE*/
//
//    private Mono<Void> onError(ServerWebExchange exchange) {
////        ServerHttpResponse response = exchange.getResponse();
////        response.setStatusCode(httpStatus);
////        return response.setComplete();
//        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        exchange.getResponse().getHeaders().add("WWW-Authenticate", "Bearer realm=\"example\"");
//        exchange.getResponse().getHeaders().add("Content-Type", "application/json");
//        String message = "{\"error\": \"" + "" + "\"}";
//        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(message.getBytes());
//        return exchange.getResponse().writeWith(Mono.just(buffer)).then(Mono.empty());
//    }
//
//    private String getAuthHeader(ServerHttpRequest request) {
//        return request.getHeaders().getOrEmpty("Authorization").get(0);
//
//    }
//
//    private boolean isAuthMissing(ServerHttpRequest request) {
//        return !request.getHeaders().containsKey("Authorization");
//    }
//
//    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
//        Claims claims = jwtUtil.getClaimsFromToken(token);
//        exchange.getRequest().mutate()
//                .header("id", String.valueOf(claims.get("id")))
//                .header("role", String.valueOf(claims.get("role")))
//                .build();
//    }
//
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//
//
//        if (this.isAuthMissing(request))
//            return this.onError(exchange);
//
//        final String token = this.parseJwt(exchange);
//
//        try {
//            if (!jwtUtil.validateJwtToken(token)){
//                return this.onError(exchange);
//            }
//        } catch (JwtTokenMalformedException e) {
//            throw new RuntimeException(e);
//        }
//
//
////            return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
//
////            this.populateRequestWithHeaders(exchange, token);
//
//        return chain.filter(exchange);
//    }
//
//    private String parseJwt(ServerWebExchange exchange) {
//        ServerHttpRequest request = exchange.getRequest();
//        String responseHeader = request.getHeaders().getOrEmpty("Authorization").get(0);
//        log.warn("responseHeader: " + responseHeader);
//
//        if (StringUtils.hasText(responseHeader) && responseHeader.startsWith("Bearer ")) {
//            return responseHeader.substring(7, responseHeader.length());
//
//        }
//        return null;
//    }

}


