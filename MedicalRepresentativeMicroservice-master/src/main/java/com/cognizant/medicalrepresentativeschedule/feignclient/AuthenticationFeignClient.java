package com.cognizant.medicalrepresentativeschedule.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.medicalrepresentativeschedule.model.JwtResponse;


@FeignClient(name = "${AUTH_FEIGN_NAME}", url = "${AUTH_FEIGN_URL}")
public interface AuthenticationFeignClient {


	@GetMapping(value = "${AUTH_FEIGN_VALIDATE_URL}")
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);
	
	@GetMapping("${AUTH_FEIGN_USERDETAILS_URL}")
	public List<String> allUserDetails();
}