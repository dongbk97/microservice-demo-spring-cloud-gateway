package com.example.productservice.controller;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProductList() {

        return ResponseEntity.ok(productRepo.getAll());

    }

    @GetMapping("/dto")
    public ResponseEntity<List<ProductDTO>> getProductDTOList() {

        return ResponseEntity.ok(productRepo.getAll2());

    }
}
