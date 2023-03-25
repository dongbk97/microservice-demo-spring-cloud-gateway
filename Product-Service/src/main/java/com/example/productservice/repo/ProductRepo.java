package com.example.productservice.repo;

import com.example.productservice.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class ProductRepo {
    public List<Product> getAll(){

        return Arrays.asList(new Product("Product-1",100),
                new Product("Product-2",100),
                new Product("Product-3",100),
                new Product("Product-41",100),
                new Product("Product-5",100),
                new Product("Product-6",100),
                new Product("Product-7",100),
                new Product("Product-8",100)
                );
    }
}
