package com.authorization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorization.pojo.LoginRequest;
import com.authorization.security.JwtTokenProvider;

import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class JwtTokenProviderTest {

	private UserDetails userDetails;

	@Autowired
	JwtTokenProvider jwtUtil;
	
	private LoginRequest loginRequest = new LoginRequest("pratik","pratik");

	@Test
	public void genrateTokenTest() {
		String token = jwtUtil.generateToken(loginRequest);
		assertNotNull(token);
	}

	@Test
	public void validateTokenTest() {
		String token = jwtUtil.generateToken(loginRequest);
		boolean isValid = jwtUtil.validateToken(token);
		
		assertTrue(isValid);
	}
	
	@Test
	public void invalidTokenTest() {
		String token = jwtUtil.generateToken(loginRequest);
		boolean isValid ;
		try {
			isValid = jwtUtil.validateToken(token + " ");
			
		}catch(SignatureException e) {
			isValid = false; 
		}
		
		assertFalse(isValid);
	}
		
	@Test
	public void getUsernameFromTokenTest() {
		String token = jwtUtil.generateToken(loginRequest);
		String username = jwtUtil.getUsernameFromJWT(token);
		
		assertEquals("pratik", username);
	}
}
