package com.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.authorization.model.User;
import com.authorization.pojo.ApiResponse;
import com.authorization.pojo.LoginRequest;
import com.authorization.pojo.SignUpRequest;
import com.authorization.security.JwtTokenProvider;
import com.authorization.service.UserRepoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(exposedHeaders = "Authorization")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepoService userRepoService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@GetMapping(path = "/validate")
	public ResponseEntity<ApiResponse> validateToken(@RequestHeader(name = "Authorization") String token) {
		
			
			if (!tokenProvider.validateToken(token.substring(7))) {
				log.info("Token is Invalid");
				return new ResponseEntity<>(new ApiResponse(false, "Invalid Token!"), HttpStatus.FORBIDDEN);
			}
		
		
		log.info("Token is Valid");
		return new ResponseEntity<>(new ApiResponse(true, "Valid Token!"), HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication;

		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = tokenProvider.generateToken(loginRequest);
			log.info(jwt);

			return ResponseEntity.ok()
								 .header("Authorization", String.format("Bearer %s", jwt))
								 .build();
		}
		catch (BadCredentialsException e) {
			log.error("Invalid credentials");
			return ResponseEntity.badRequest()
								 .body("Invalid Credentials");

		}

	}

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> registerUser(@RequestBody SignUpRequest signUpRequest) {

		if (userRepoService.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

	
		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
				passwordEncoder.encode(signUpRequest.getPassword()));


		userRepoService.createUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "User registered successfully"));
	}
}
