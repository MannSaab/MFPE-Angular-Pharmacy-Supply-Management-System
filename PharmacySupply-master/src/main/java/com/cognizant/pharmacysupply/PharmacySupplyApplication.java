package com.cognizant.pharmacysupply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PharmacySupplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacySupplyApplication.class, args);
	}

}
