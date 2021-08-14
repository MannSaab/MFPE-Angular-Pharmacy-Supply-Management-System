package com.medicine.stock.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class MedicineStockTest {
	
	private MedicineStock medicineStock;
	
	
	@Before
	public void setStockUsingArgsConstructor() {
		medicineStock = 
				new MedicineStock(1,"Crosin","chemical names","General",new Date(),200,"Nagpur"); 
	}

	@Test
	public void medicineStockTest() throws Exception{
		assertNotNull(medicineStock);
		assertTrue(200 == medicineStock.getNumberOfTabletsInStock());
		assertTrue("Crosin".equals(medicineStock.getMedicineName()));
		assertTrue("chemical names".equals(medicineStock.getChemicalComposition()));
		assertTrue("General".equals(medicineStock.getTargetAilment()));
		assertTrue("Nagpur".equals(medicineStock.getPharmacyName()));
		assertTrue((new Date()).equals(medicineStock.getDateOfExpiry()));
	}
	
	
	@Test
	public void noArgsConstrutorTest() {		
		 MedicineStock medicineStock1 = new MedicineStock();
		
		assertNotNull(medicineStock1);		
		
		medicineStock1.setMedicineId(1);
		medicineStock1.setMedicineName("Crosin");
		medicineStock1.setChemicalComposition("chemical names");
		medicineStock1.setDateOfExpiry(new Date());
		medicineStock1.setPharmacyName("Nagpur");
		medicineStock1.setTargetAilment("General");
		medicineStock1.setNumberOfTabletsInStock(200);
		
		assertEquals(medicineStock.toString(), medicineStock1.toString());
	}
	
	
}

