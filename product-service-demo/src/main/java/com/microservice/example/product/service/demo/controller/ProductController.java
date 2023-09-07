package com.microservice.example.product.service.demo.controller;

import com.microservice.example.product.service.demo.dto.ProductRequest;
import com.microservice.example.product.service.demo.dto.ProductRespose;
import com.microservice.example.product.service.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductRespose> getAllProducts(){
        return productService.getAllProducts();
    }

}
