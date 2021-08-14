package com.cognizant.pharmacysupply.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;
import com.cognizant.pharmacysupply.service.PharmacySupplyService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class PharmacySupplyController {
	
	@Autowired
	private PharmacySupplyService pharmacySupplyService;
	
	@PostMapping("/pharmacy-supply")
	public ResponseEntity<List<PharmacyMedicineSupply>> getPharmacySupply(@RequestHeader("Authorization") String token,
			@RequestBody List<MedicineDemand> medicineDemandList){
		
		List<PharmacyMedicineSupply> pharmacySupplyList = null;
		try {
			if(pharmacySupplyService.validate(token).isSuccess()) {
				pharmacySupplyList = pharmacySupplyService.getPharmacySupplyCount(medicineDemandList);
				
				if (pharmacySupplyList == null)  {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				}
				
				return new ResponseEntity<>(pharmacySupplyList, HttpStatus.OK);
			}
			
		}
		catch(Exception e) {
			log.error(e.getMessage());
			
		}
		
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		
	}
	
	
	@GetMapping("/viewDemand")
	public ResponseEntity<List<MedicineDemand>> getDemandDetails(@RequestHeader("Authorization") String token){
		
		List<MedicineDemand> medicineDemandList = null;
		
		try {
		if(pharmacySupplyService.validate(token).isSuccess()) {
			
			medicineDemandList = pharmacySupplyService.getMedicineDemand();
			
			return new ResponseEntity<>(medicineDemandList, HttpStatus.OK);
		}
		}
		catch(FeignException e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/pharmacy-medicine-supply")
	public ResponseEntity<List<PharmacyMedicineSupply>> getPharmacyMedicineSupplyDetails(@RequestHeader("Authorization") String token){
		
		List<PharmacyMedicineSupply> medicineDemandList = null;
		
		try {
			if(pharmacySupplyService.validate(token).isSuccess()) {
				
				medicineDemandList = pharmacySupplyService.getPharmacySupplyDetails();
				
				return new ResponseEntity<>(medicineDemandList, HttpStatus.OK);
			}
		}
		catch(FeignException e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	

}
