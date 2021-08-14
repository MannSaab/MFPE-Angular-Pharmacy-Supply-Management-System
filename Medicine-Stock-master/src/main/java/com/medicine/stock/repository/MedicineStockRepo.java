package com.medicine.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicine.stock.model.MedicineStock;

@Repository
public interface MedicineStockRepo extends JpaRepository<MedicineStock, Integer> {

	List<MedicineStock> findByTargetAilment(String targetAilment);
	
}
