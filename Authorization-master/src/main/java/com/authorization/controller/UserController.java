package com.authorization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.authorization.pojo.UserIdentityAvailability;
import com.authorization.service.UserRepoService;

@RestController
@RequestMapping
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepoService userRepoService;

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepoService.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

 
    @GetMapping("/user-details")
    public List<String> allUserDetails()
    {
    	return userRepoService.getAllUserByUsername();
    }

}
