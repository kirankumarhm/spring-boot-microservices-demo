package com.microservice.example.order.service.demo.service;

import com.microservice.example.order.service.demo.dto.OrderLineItemsDto;
import com.microservice.example.order.service.demo.dto.OrderRequest;
import com.microservice.example.order.service.demo.model.Order;
import com.microservice.example.order.service.demo.model.OrderLineItems;
import com.microservice.example.order.service.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::maoToDto)
                .toList();

        order.setOrderLineItems(orderLineItems);

        orderRepository.save(order);

    }

    private OrderLineItems maoToDto(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .skuCode(orderLineItemsDto.getSkuCode())
                .quantity(orderLineItemsDto.getQuantity())
                .build();
    }


}
