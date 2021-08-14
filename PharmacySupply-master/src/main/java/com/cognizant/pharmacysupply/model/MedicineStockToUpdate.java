package com.cognizant.pharmacysupply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineStockToUpdate {
	
	private String medicineName;
	private Integer supplyCount;

}
