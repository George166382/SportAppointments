package com.example.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.application.entities.Admin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.entities.Person;
import com.example.application.entities.User;
import com.example.application.services.AdminService;
import com.example.application.services.UserService;

@RestController
@RequestMapping("/api/v1/users/sign-up")
public class SignUpUserController {

    
    @Autowired
    private UserService serviceUser;

    @PostMapping
    public ResponseEntity<String> createEntity(@RequestBody User person) {
        try {
            	if(person instanceof User) {
            		serviceUser.addUser(person);
            		return ResponseEntity.ok("{\"message\": \"User created successfully.\"}");
            	}

            	else {
            		return ResponseEntity.badRequest().body("{\"error\": \"Invalid type\"}"); 
            	}
        } catch (Exception e) {
            // Handle any unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred: " + e.getMessage() + "\"}");
        }
    }
   
}
