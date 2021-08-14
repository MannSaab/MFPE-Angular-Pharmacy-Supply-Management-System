package com.medicine.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MedicineStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicineStockApplication.class, args);
	}

}
