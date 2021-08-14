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
public class SignUpRequestTest {

		private SignUpRequest signUpRequest;

		@Before
		public void setUp() {
			signUpRequest = new SignUpRequest("admin", "admin", "root");
		}

		@Test
		public void testAllArgumentConstructor() {
			SignUpRequest signUpRequest = new SignUpRequest("admin", "admin", "root");
			assertEquals("admin", signUpRequest.getName());
			assertEquals("admin", signUpRequest.getUsername());
			assertEquals("admin", signUpRequest.getPassword());
		}

		@Test
		public void testNoArgumentConstructor() {
			SignUpRequest signUpRequest = new SignUpRequest();
			assertEquals(signUpRequest, signUpRequest);
		}

		@Test
		public void testSetter() {
			SignUpRequest user = new SignUpRequest();
			user.setUsername("admin");
			assertEquals("admin", user.getUsername());
		}

		@Test
		public void testEqualsMethod() {
			boolean equals = signUpRequest.equals(signUpRequest);
			assertTrue(equals);
		}
	

}
