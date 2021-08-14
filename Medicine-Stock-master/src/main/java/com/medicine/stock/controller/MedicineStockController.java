package com.medicine.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicine.stock.model.MedicineStock;
import com.medicine.stock.pojo.ApiResponse;
import com.medicine.stock.pojo.MedicineSupply;
import com.medicine.stock.service.MedicineStockRepoService;

@RestController
@CrossOrigin
public class MedicineStockController {
	
	@Autowired
	MedicineStockRepoService medicineStockService;
	
	
	@GetMapping("/get-medicine-stock")
	public ResponseEntity<List<MedicineStock>> getMedicineStock(){
		
		return new ResponseEntity<>(medicineStockService.getMedicineStockDeatils(),HttpStatus.OK);
	}

	
	@GetMapping("/medicineByAilment/{targetAilment}")
	public ResponseEntity<List<String>> getMedicineByAilment(@PathVariable String targetAilment){
		try {
		
			return new ResponseEntity<>(medicineStockService.getMedicineStockByAilment(targetAilment), HttpStatus.OK);
	
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	

	
	
	@PostMapping("/update-stock")
	public ResponseEntity<ApiResponse> updateMedicineStock(
			@RequestBody List<MedicineSupply> updateMedicineStockInfo) {
		
		if(medicineStockService.updateMedicineStock(updateMedicineStockInfo)) {
			return new ResponseEntity<>(new ApiResponse(true,"Updated Successfully"),
					HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new ApiResponse(false,"Unsuccessful"),HttpStatus.BAD_REQUEST);
		
	}
}
