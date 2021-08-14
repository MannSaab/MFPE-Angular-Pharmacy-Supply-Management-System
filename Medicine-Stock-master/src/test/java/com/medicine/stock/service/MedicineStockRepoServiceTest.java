package com.medicine.stock.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.medicine.stock.model.MedicineStock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicineStockRepoServiceTest {
	
	@Autowired
	private MedicineStockRepoService medicineStockService;
	

	@Mock
	private MedicineStock medicineStock;
	
	@Test
	public void getMedicineStockInformationTest() {
		
		List<MedicineStock> medicineStockList = medicineStockService.getMedicineStockDeatils();
		
		assertNotNull(medicineStockList);
	}
	
	@Test
	public void getStockByTargetAliment() {
		
		List<String> medicineStock = null;
		
		medicineStock = medicineStockService.getMedicineStockByAilment("General");
		
		assertNotNull(medicineStock);
		
		
	}
}
