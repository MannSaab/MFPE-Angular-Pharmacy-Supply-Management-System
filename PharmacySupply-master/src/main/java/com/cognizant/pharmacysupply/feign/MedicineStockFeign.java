package com.cognizant.pharmacysupply.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.pharmacysupply.model.ApiResponse;
import com.cognizant.pharmacysupply.model.MedicineStock;
import com.cognizant.pharmacysupply.model.MedicineStockToUpdate;


@FeignClient(url = "${MEDICINE_STOCK_FEIGN_URL}", name = "${MEDICINE_STOCK_FEIGN_NAME}")
public interface MedicineStockFeign {
	
	@GetMapping("${MEDICINE_STOCK_GET_MEDICINE_STOCK_URL}")
	public List<MedicineStock> getMedicineStock();
	
	@PostMapping("${MEDICINE_STOCK_GET_MEDICINE_URL}")
	public ApiResponse updateMedicineStock(
			@RequestBody List<MedicineStockToUpdate> updateMedicineStockInfo);
}
