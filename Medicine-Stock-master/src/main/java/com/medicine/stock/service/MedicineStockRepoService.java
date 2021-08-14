package com.medicine.stock.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicine.stock.model.MedicineStock;
import com.medicine.stock.pojo.MedicineSupply;
import com.medicine.stock.repository.MedicineStockRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MedicineStockRepoService {
	
	@Autowired
	MedicineStockRepo  medicineStockRepo;
	
	
	public List<MedicineStock> getMedicineStockDeatils(){
		
		return medicineStockRepo.findAll();
	}
	
	public List<String> getMedicineStockByAilment(String targetAliment){
		
		return medicineStockRepo
					.findByTargetAilment(targetAliment)
					.stream()
					.map(MedicineStock :: getMedicineName)
					.collect(Collectors.toList());
	}


	@Transactional
	public boolean updateMedicineStock(List<MedicineSupply> updateMedicineStock) {
		
		List<MedicineStock> medicineDetails = medicineStockRepo.findAll();
		for(MedicineSupply updateMedicine : updateMedicineStock) {
// 			try {
// 				Optional<MedicineStock> medicineDetails = 
// 					medicineStockRepo.findAll()
// 											.stream()
// 											.filter(medicine -> medicine.getMedicineName().equals(updateMedicine.getMedicineName()))
// 											.findAny();
				
				
// 				if(!medicineDetails.isEmpty()) {
// 					MedicineStock medicine = medicineDetails.get();
// 					medicine.setNumberOfTabletsInStock(updateMedicine.getSupplyCount());
// 					medicineStockRepo.save(medicine);
					
// 				}}
// 				catch(NoSuchElementException e) {
// 					log.error(e.getMessage());
// 					return false;
// 				}
			
			
				
				for(MedicineStock medicine : medicineDetails) {
					if(medicine.getMedicineName().equals(updateMedicine.getMedicineName())){
						medicine.setNumberOfTabletsInStock(updateMedicine.getSupplyCount());
						medicineStockRepo.save(medicine);
					}
				}
		}
		
		return true;
	}
}

