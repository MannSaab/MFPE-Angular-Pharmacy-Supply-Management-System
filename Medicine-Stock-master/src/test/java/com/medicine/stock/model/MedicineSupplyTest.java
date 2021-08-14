package com.medicine.stock.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.medicine.stock.pojo.MedicineSupply;

public class MedicineSupplyTest {
	
	private MedicineSupply medicineSupply;

	@Before
	public void setStockUsingArgsConstructor() {
		medicineSupply = 
				new MedicineSupply("Crocin",200); 
	}

	@Test
	public void medicineStockTest() throws Exception{
		assertNotNull(medicineSupply);
		assertEquals("Crocin", medicineSupply.getMedicineName());
		assertTrue((1) == (medicineSupply.getSupplyCount()));
	}
	
	
	@Test
	public void noArgsConstrutorTest() {		
		 MedicineSupply medicineSupply = new MedicineSupply();
		
		assertNotNull(medicineSupply);		
		
		medicineSupply.setSupplyCount(100);
		medicineSupply.setMedicineName("Crosin");
		
	}
	
}
