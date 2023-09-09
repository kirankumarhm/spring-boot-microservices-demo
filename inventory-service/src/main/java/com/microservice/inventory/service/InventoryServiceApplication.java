package com.microservice.inventory.service;

import com.microservice.inventory.service.model.Inventory;
import com.microservice.inventory.service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	// Add the characters %20 to represent a space within your PATH parameters, for example:
	// http://localhost:8083/api/inventory/iPhone%2013%20Red

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory1 = Inventory.builder()
					.skuCode("iPhone 12")
					.quantity(100)
					.build();

			Inventory inventory2 = Inventory.builder()
					.skuCode("iPhone 13")
					.quantity(200)
					.build();

			Inventory inventory3 = Inventory.builder()
					.skuCode("iPhone 13 Red")
					.quantity(0)
					.build();
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
			inventoryRepository.save(inventory3);
		};
	}
}
