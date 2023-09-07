package com.microservice.example.order.service.demo.controller;

import com.microservice.example.order.service.demo.dto.OrderRequest;
import com.microservice.example.order.service.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<ProductRespose> getAllProducts(){
//        return productService.getAllProducts();
//    }
}
