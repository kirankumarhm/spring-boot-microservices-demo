package com.microservice.example.order.service.demo.repository;

import com.microservice.example.order.service.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
