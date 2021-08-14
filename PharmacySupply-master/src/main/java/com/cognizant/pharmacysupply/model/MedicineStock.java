package com.cognizant.pharmacysupply.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicineStock {

	
	private Integer medicineId;
	private String medicineName;
	private String chemicalComposition;
	private String targetAilment;
	private Date dateOfExpiry;
	private Integer numberOfTabletsInStock;
	private String pharmacyName;

}