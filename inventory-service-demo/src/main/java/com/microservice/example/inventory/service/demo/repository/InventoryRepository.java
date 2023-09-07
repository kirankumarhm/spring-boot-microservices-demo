package com.microservice.example.inventory.service.demo.repository;

import com.microservice.example.inventory.service.demo.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Object> findBySkuCode(String skuCode);
}
