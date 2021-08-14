package com.cognizant.pharmacysupply.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.pharmacysupply.exception.TokenValidationFailedException;
import com.cognizant.pharmacysupply.feign.AuthenticationFeignClient;
import com.cognizant.pharmacysupply.feign.MedicineStockFeign;
import com.cognizant.pharmacysupply.model.ApiResponse;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.MedicineStock;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;
import com.cognizant.pharmacysupply.repository.PharmacistsRepository;

import feign.FeignException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PharmacySupplyServiceTest { 
	
	@Autowired
	private PharmacySupplyService pharmacySupplyService;

	@Mock
	private MedicineDemand medicineDemand;

	@Mock
	private PharmacyMedicineSupply pharmacyMedicineSupply;

	@Mock
	private MedicineStock medicineStock;

	@MockBean
	private AuthenticationFeignClient authFeignClient;
	
	@Mock
	private MedicineStockFeign medicineStockFeign;
	
	@Mock
	private PharmacistsRepository pharmacistsRepository;
		
	private List<MedicineDemand> medicineDemandList = new ArrayList<MedicineDemand>();
	
	@Before
	public void setup() { 
		medicineDemand = new MedicineDemand(1, "Crocin", 100);
		medicineDemandList.add(medicineDemand);
		pharmacyMedicineSupply = new PharmacyMedicineSupply(1, "Healthy Pharmacy", "Crocin", 200);
		medicineStock = new MedicineStock(1, "Crocin", "digoxin", "General",new java.util.Date(2022 - 9 - 12), 500, "Healthy Pharmacy");
	}
	
	
	@Test(expected = TokenValidationFailedException.class)
	public void testValidateTokenFail() {
		when(authFeignClient.validateToken("token")).thenReturn(new ApiResponse(false,"Token Invalid"));
		pharmacySupplyService.validate("token");
	}
	
	@Test
	public void getStockDetailsTest() throws FeignException {
		FeignException thrown = assertThrows(FeignException.class,
				() -> medicineStockFeign.getMedicineStock());

		assertTrue(thrown.getMessage().contains("Medicine Stock not Found"));
	}
	
	
}
