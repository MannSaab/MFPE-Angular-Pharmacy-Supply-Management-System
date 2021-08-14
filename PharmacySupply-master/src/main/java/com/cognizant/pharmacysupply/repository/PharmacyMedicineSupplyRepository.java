package com.cognizant.pharmacysupply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;

@Repository
public interface PharmacyMedicineSupplyRepository extends JpaRepository<PharmacyMedicineSupply, Integer>{

	public List<PharmacyMedicineSupply> findTop20ByOrderByIdDesc();
}
