package com.cognizant.pharmacysupply.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="medicine_demand")
@ToString
public class MedicineDemand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String medicineName;
	
	private int demandCount;
	
	private int fulfilled;
		
	private LocalDate demandDate;

	public MedicineDemand(String medicineName, int demandCount, LocalDate demandDate, int fulfilled) {
		super();
		this.medicineName = medicineName;
		this.demandCount = demandCount;
		this.demandDate = demandDate;
		this.fulfilled = fulfilled;
		
	}

	public MedicineDemand(int id, String medicineName, int demandCount) {
		super();
		this.id = id;
		this.medicineName = medicineName;
		this.demandCount = demandCount;
	}
	
	
}
