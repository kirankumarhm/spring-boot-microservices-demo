package com.microservice.example.product.service.demo.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.microservice.example.product.service.demo.dto.ProductRequest;
import com.microservice.example.product.service.demo.dto.ProductRespose;
import com.microservice.example.product.service.demo.model.Product;
import com.microservice.example.product.service.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void createProduct(ProductRequest productRequest){

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductRespose> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
        // OR We can write
//        return products.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());
    }

    private ProductRespose mapToProductResponse(Product product) {
        return ProductRespose.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
