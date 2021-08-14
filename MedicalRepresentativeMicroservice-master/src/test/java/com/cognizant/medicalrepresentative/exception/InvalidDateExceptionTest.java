package com.cognizant.medicalrepresentative.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.medicalrepresentativeschedule.exception.InvalidDateException;

@SpringBootTest
public class InvalidDateExceptionTest {

	@Mock
	private InvalidDateException dateNtFound;

	@Test
	public void testOneArgConstructor() {
		InvalidDateException exception = new InvalidDateException("Invalid date.");
		assertEquals("Invalid date.", exception.getMessage());
	}

	@Test
	public void testNoArgConstructor() {
		InvalidDateException exception = new InvalidDateException();
		assertEquals(null, exception.getMessage());
	}

}