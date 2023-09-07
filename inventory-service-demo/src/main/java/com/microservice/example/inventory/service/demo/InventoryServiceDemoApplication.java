package com.microservice.example.inventory.service.demo;

import com.microservice.example.inventory.service.demo.model.Inventory;
import com.microservice.example.inventory.service.demo.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceDemoApplication.class, args);
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
					.quantity(200)
					.build();
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
			inventoryRepository.save(inventory3);
		};
	}

}
