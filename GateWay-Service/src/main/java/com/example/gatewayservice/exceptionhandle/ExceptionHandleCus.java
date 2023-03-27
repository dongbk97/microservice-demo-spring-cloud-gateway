package com.example.gatewayservice.exceptionhandle;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandleCus  extends ResponseEntityExceptionHandler {




    @ExceptionHandler({MalformedJwtException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<Object>> handleAllException(MalformedJwtException ex) {
        ErrorCus errorCus= new ErrorCus(LocalDateTime.now(), ex.getMessage());
        return Mono.just(new ResponseEntity<>(errorCus, HttpStatus.NOT_FOUND));

    }
    @ExceptionHandler({ExpiredJwtException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<Object>> handleAllException1(ExpiredJwtException ex) {
        ErrorCus errorCus= new ErrorCus(LocalDateTime.now(), ex.getMessage());
        return Mono.just(new ResponseEntity<>(errorCus, HttpStatus.NOT_FOUND));

    }
    @ExceptionHandler({UnsupportedJwtException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<Object>> handleAllException2(UnsupportedJwtException ex) {
        ErrorCus errorCus= new ErrorCus(LocalDateTime.now(), ex.getMessage());
        return Mono.just(new ResponseEntity<>(errorCus, HttpStatus.NOT_FOUND));

    }
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<Object>> handleAllException3(IllegalArgumentException ex) {
        ErrorCus errorCus= new ErrorCus(LocalDateTime.now(), ex.getMessage());
        return Mono.just(new ResponseEntity<>(errorCus, HttpStatus.NOT_FOUND));

    }
}
