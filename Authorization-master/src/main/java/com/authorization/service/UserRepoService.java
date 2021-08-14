package com.authorization.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authorization.model.User;
import com.authorization.repository.UserRepository;

@Service
public class UserRepoService {

	@Autowired
	UserRepository userRepository;

	public void createUser(User user) {
		userRepository.save(user);
	}
	
	public boolean existsByUsername(String username) {
		
		return userRepository.existsByUsername(username);
	}
	
	public List<String> getAllUserByUsername(){
		List<User> users = userRepository.findAll();
		
		return users.stream()
					.map(User :: getName)
					.collect(Collectors.toList());
	}
		

}
