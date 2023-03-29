package com.example.gatewayservice.jwt.gatewayfilter;

import com.example.gatewayservice.exceptionhandle.JwtTokenMalformedException;
import com.example.gatewayservice.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
public class GateWayFilterCus implements GatewayFilter {


    @Autowired
    private JwtUtils jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

        final List<String> apiEndpoints = List.of("/register", "/login");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey("Authorization")) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);

                return response.setComplete();
            }

            final String token = parseJwt(exchange);
//                    request.getHeaders().getOrEmpty("Authorization").get(0);

            try {
                jwtUtil.validateJwtToken(token);
            } catch (JwtTokenMalformedException | ExpiredJwtException | MalformedJwtException
                     | UnsupportedJwtException | IllegalArgumentException | DecodingException | SignatureException
                    e2) {
                // e.printStackTrace();

                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                String message = "{\"error\": \"" + e2.getMessage() + "\"}";
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(message.getBytes());
                response.writeWith(Mono.just(buffer)).then(Mono.empty());
                return response.setComplete();
            }

            Claims claims = jwtUtil.getClaimsFromToken(token);
            exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
        }

        return chain.filter(exchange);
    }

    private String parseJwt(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String responseHeader = request.getHeaders().getOrEmpty("Authorization").get(0);

        if (StringUtils.hasText(responseHeader) && responseHeader.startsWith("Bearer ")) {
            return responseHeader.substring(7, responseHeader.length());

        }
        return null;
        // test git merge : remote
    }


    // test merge
}

