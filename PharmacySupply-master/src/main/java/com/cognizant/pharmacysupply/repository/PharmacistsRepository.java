package com.cognizant.pharmacysupply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.pharmacysupply.model.Pharmacists;

@Repository
public interface PharmacistsRepository extends JpaRepository<Pharmacists, Integer>{

	
}
