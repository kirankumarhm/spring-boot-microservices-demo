package com.microservice.example.inventory.service.demo.controller;

import com.microservice.example.inventory.service.demo.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable(name = "sku-code") String skuCode){
        return inventoryService.isInStock(skuCode);
    }

    // Add the characters %20 to represent a space within your PATH parameters, for example:
    // http://localhost:8083/api/inventory/iPhone%2013%20Red

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<ProductRespose> getAllProducts(){
//        return productService.getAllProducts();
//    }
}
