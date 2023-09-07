package com.microservice.example.product.service.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRespose {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
