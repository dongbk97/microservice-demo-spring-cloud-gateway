package com.example.productservice.repo;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductRepo {
    public List<Product> getAll() {

        return Arrays.asList(new Product("Product-1", 100),
                new Product("Product-2", 100),
                new Product("Product-3", 100),
                new Product("Product-41", 100),
                new Product("Product-5", 100),
                new Product("Product-6", 100),
                new Product("Product-7", 100),
                new Product("Product-8", 100)
        );
    }

    public List<ProductDTO> getAll2() {
        List<ProductDTO> dtoList = new ArrayList<ProductDTO>();
        this.getAll().forEach(t ->
                {
                    ProductDTO productDTO = new ProductDTO();
                    BeanUtils.copyProperties(t, productDTO);
                    dtoList.add(productDTO);
                }
        );

        return dtoList;
    }


}
