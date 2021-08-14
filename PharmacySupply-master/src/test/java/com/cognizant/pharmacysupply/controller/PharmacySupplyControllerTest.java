package com.cognizant.pharmacysupply.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.pharmacysupply.exception.TokenValidationFailedException;
import com.cognizant.pharmacysupply.feign.AuthenticationFeignClient;
import com.cognizant.pharmacysupply.model.ApiResponse;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;
import com.cognizant.pharmacysupply.service.PharmacySupplyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PharmacySupplyControllerTest {
	

	@Mock
	private AuthenticationFeignClient authFeignClient;
	
	@InjectMocks
	private PharmacySupplyController pharmacyController;
	
	@Mock
	private MedicineDemand medicineDemand;

	@Mock
	private PharmacyMedicineSupply pharmacyMedicineSupply;

	@Mock
	private List<MedicineDemand> medicineDemandList;
	
	@Mock
	private PharmacySupplyService pharmacyService;
	
	@Before
	public void setup() {
		medicineDemand = new MedicineDemand();
		medicineDemand.setId(1);
		medicineDemand.setDemandCount(200);
		medicineDemand.setMedicineName("Crocin");
		pharmacyMedicineSupply = new PharmacyMedicineSupply();
		pharmacyMedicineSupply.setId(1);
		pharmacyMedicineSupply.setMedicineName("Crocin");
		pharmacyMedicineSupply.setPharmacyName("Healthy pharmacy");
		pharmacyMedicineSupply.setSupplyCount(200);
	}
	
	@Test
	public void testGetPharmacySupply() throws Exception {
		when(pharmacyService.validate("token")).thenReturn(new ApiResponse(true, "Token Valid"));
		ResponseEntity<?> pharmacySupplyList = pharmacyController.getPharmacySupply("token", medicineDemandList);
		HttpStatus statusCode = pharmacySupplyList.getStatusCode();
		assertNotNull(statusCode);
		assertEquals(HttpStatus.OK, statusCode);
	}
	
	@Test(expected = TokenValidationFailedException.class)
	public void testGetPharmacySupplyWhenTokenNotValid() throws Exception {
		when(authFeignClient.validateToken("token")).thenReturn(new ApiResponse(false,"Token Invaid"));
		ResponseEntity<?> pharmacySupplyList = pharmacyController.getPharmacySupply("token",
				medicineDemandList);
		assertNotNull(pharmacySupplyList);
	}

}
