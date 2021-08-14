package com.authorization.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
public class LoginRequestTest {

	
	private LoginRequest loginRequest;
	
	@Before
	public void setUp() {
		loginRequest = new LoginRequest("admin","admin");
	}
	
	@Test
	public void testAllArgumentConstructor() {
		LoginRequest userLog =new LoginRequest("admin","admin");
		assertEquals("admin",userLog.getUsername());
	}
	
	@Test
	public void testEquals() {
		boolean res= loginRequest.equals(loginRequest);
		assertTrue(res);
	}
	
	@Test
	public void testNoArgConstructor() {
		LoginRequest loginRequest=new LoginRequest();
		assertEquals(loginRequest,loginRequest);
	}
}
