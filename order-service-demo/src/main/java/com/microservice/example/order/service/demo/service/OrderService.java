package com.microservice.example.order.service.demo.service;

import com.microservice.example.order.service.demo.dto.InventoryResponse;
import com.microservice.example.order.service.demo.dto.OrderLineItemsDto;
import com.microservice.example.order.service.demo.dto.OrderRequest;
import com.microservice.example.order.service.demo.model.Order;
import com.microservice.example.order.service.demo.model.OrderLineItems;
import com.microservice.example.order.service.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

// Note : When class level annotation @RequiredArgsConstructor is used the constructor argument is not required

//    public OrderService(OrderRepository orderRepository, WebClient webClient) {
//        this.orderRepository = orderRepository;
//        this.webClient = webClient;
//    }

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::maoToDto)
                .toList();

        order.setOrderLineItems(orderLineItems);

        List<String> skuCodeList = order.getOrderLineItems().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        InventoryResponse[] inventoryResponsesArray = webClient.get()
                .uri("http://localhost:8082/api/inventory", uriBuilder ->
                    uriBuilder.queryParam("skuCode", skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock);

        if(allProductsInStock) {
            orderRepository.save(order);
        }else{
            throw  new IllegalArgumentException("Product is not in stock");
        }

    }

    private OrderLineItems maoToDto(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .skuCode(orderLineItemsDto.getSkuCode())
                .quantity(orderLineItemsDto.getQuantity())
                .build();
    }


}
