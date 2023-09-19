package com.example.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.controllers.dto.UserDTO;
import com.example.application.entities.User;
import com.example.application.services.AdminService;
import com.example.application.services.UserService;


@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	
	
	
	  @GetMapping
	  public List<UserDTO> getUsers() 
	  { 
		  return userService.getUsers(); 
	  }
	 
	  @PostMapping
	  public void addUser(@RequestBody User user)
	  {
		  userService.addUser(user);
	  }
	  @PutMapping
	  public void updateUser(@RequestParam(name="email")String email,@RequestParam(name="idUser")Long id)
	  {
		  userService.updateUser(email,id);
	  }
	  @DeleteMapping
	  public void deleteUser(@PathVariable("id") Long id)
	  {
		  userService.deleteUser(id);
	  }
	  
	
	
}
