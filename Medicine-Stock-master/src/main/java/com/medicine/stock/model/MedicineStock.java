package com.medicine.stock.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Entity
public class MedicineStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer medicineId;
	private String medicineName;
	private String chemicalComposition;
	private String targetAilment;
	@Temporal(TemporalType.DATE)
	private Date dateOfExpiry;
	private Integer numberOfTabletsInStock;
	private String pharmacyName;

}
