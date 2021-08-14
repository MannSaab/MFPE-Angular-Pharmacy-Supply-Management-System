package com.medicine.stock.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Before;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiResponseTest {
	
	@Mock
	public ApiResponse apiResponse;
	
	@Before
	public void setUp() throws Exception{
		apiResponse = new ApiResponse();
		apiResponse.setMessage("Token Invalid");
		apiResponse.setSuccess(false);
	}
		
	@Test
	public void AllArgConstTest() {
		ApiResponse auth = new ApiResponse(false,"Token Invalid");
		assertEquals(apiResponse.getMessage(), auth.getMessage());
	}
	
	@Test
	public void testNoArgsConstructor() {
		
		ApiResponseTest apiResponse = new ApiResponseTest();
		assertEquals(apiResponse, apiResponse);
	}
	
	@Test
	public void testSetters() {
		apiResponse.setMessage("Token Valid");
		apiResponse.setSuccess(true);
		assertEquals("Token Valid", apiResponse.getMessage());
	}
	
	@Test
	public void testEqualsMethod() {
		boolean equals = apiResponse.equals(apiResponse);
		assertTrue(equals);
	}

}
