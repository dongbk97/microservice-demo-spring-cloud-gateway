package com.example.cartservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/cart/")
public class CartController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public ResponseEntity<List> getProducts() {

        ResponseEntity<List> products = restTemplate.getForEntity("http://localhost:8080/product/", List.class);

        return products;
    }
    @GetMapping("/dto")
    public ResponseEntity<List> getProductDTOs() {

        ResponseEntity<List> products = restTemplate.getForEntity("http://localhost:8080/product/dto", List.class);

        return products;
    }
}
