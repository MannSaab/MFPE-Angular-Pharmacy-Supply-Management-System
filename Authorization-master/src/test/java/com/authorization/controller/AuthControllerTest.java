package com.authorization.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorization.exception.BadRequestException;
import com.authorization.pojo.LoginRequest;
import com.authorization.security.JwtTokenProvider;
import com.authorization.service.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class AuthControllerTest {
	
	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private JwtTokenProvider jwtTokenProvider;

	@Mock
	private CustomUserDetailsService userDetailsService;
	
	@InjectMocks
	private AuthController authController;

	@Test
	public void testValidLogin() throws BadRequestException, LoginException  {
		LoginRequest authenticationRequest = new LoginRequest("pratik", "pratik");
		UserDetails userDetails = new User(authenticationRequest.getUsername(), authenticationRequest.getPassword(),
				new ArrayList<>());

		when(userDetailsService.loadUserByUsername("pratik")).thenReturn(userDetails);
		when(jwtTokenProvider.generateToken(authenticationRequest)).thenReturn("dummy-token");

		ResponseEntity<?> authenticationResponse = authController.authenticateUser(authenticationRequest);
		assertEquals(HttpStatus.OK, authenticationResponse.getStatusCode());
	}


	
	@Test
	public void testValidToken() throws BadRequestException, LoginException {
		LoginRequest authenticationRequest = new LoginRequest("pratik", "pratik");
		UserDetails userDetails = new User(authenticationRequest.getUsername(), authenticationRequest.getPassword(),
				new ArrayList<>());
		when(jwtTokenProvider.generateToken(authenticationRequest)).thenReturn("token");
		when(jwtTokenProvider.validateToken("token")).thenReturn(true);
		when(jwtTokenProvider.getUsernameFromJWT("token")).thenReturn("pratik");
		when(userDetailsService.loadUserByUsername("pratik")).thenReturn(userDetails);

		ResponseEntity<?> validity = authController.authenticateUser(authenticationRequest);
		assertEquals(HttpStatus.OK,validity.getStatusCode());
	}

	@Test
	public void testInvalidLogin() throws BadRequestException, LoginException {
		LoginRequest authenticationRequest = new LoginRequest("admin1", "abhishek1");
		UserDetails userDetails = new User(authenticationRequest.getUsername(),"admin1", new ArrayList<>());
		when(userDetailsService.loadUserByUsername("admin1")).thenReturn(userDetails);
		ResponseEntity<?> validity = authController.authenticateUser(authenticationRequest);
		assertEquals(HttpStatus.OK,validity.getStatusCode());
		
	}
	
	
}
