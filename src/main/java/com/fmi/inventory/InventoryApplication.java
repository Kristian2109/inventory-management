package com.fmi.inventory;

import com.fmi.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryApplication implements CommandLineRunner  {
	@Autowired
	private InventoryService inventoryService;

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		inventoryService.addItem("RC Car", "High-speed remote control car", 5, "S123", "pcs", "Vehicles", true);
		inventoryService.addItem("Battery Pack", "Rechargeable battery", 10, "S1231", "pcs", "Accessories", true);

		System.out.println(inventoryService.getAllItems());
	}
}
