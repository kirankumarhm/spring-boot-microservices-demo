package com.microservice.inventory.service.controller;

import com.microservice.inventory.service.dto.InventoryResponse;
import com.microservice.inventory.service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }

    // When I used @PathVariable
    // Add the characters %20 to represent a space within your PATH parameters, for example:
    // http://localhost:8083/api/inventory/iPhone%2013%20Red

    // http://localhost:8083/api/inventory?skuCode="iPhone 13 Red"


}
