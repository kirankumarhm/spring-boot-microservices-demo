package com.microservice.product.service.service;

import com.microservice.product.service.dto.ProductRequest;
import com.microservice.product.service.dto.ProductRespose;
import com.microservice.product.service.model.Product;
import com.microservice.product.service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
