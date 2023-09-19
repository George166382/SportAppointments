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

import com.example.application.controllers.dto.AdminDTO;
import com.example.application.entities.Admin;
import com.example.application.entities.User;
import com.example.application.services.AdminService;


@RestController
@RequestMapping
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	
	
	@GetMapping(path = "/api/v1/admins")
	  public List<AdminDTO> getAdmins() 
	  { 
		  return adminService.getAdmins(); 
	  }
	
	@PostMapping(path = "api/v1/admins/postadmins")
	 public void createAdmin(@RequestBody Admin admin)
	 {
		adminService.createAdmin(admin);
	 }
	
	@PutMapping(path = "api/v1/admins/putadmins")
	public void updateAdmin(@RequestParam(name="name") String name, @RequestParam(name="id") Long id)
	{
		adminService.updateAdmin(name,id);
	}
	
	@DeleteMapping(path = "api/v1/admins/deleteadmins/{adminId}")
	public void deleteAdmin(@PathVariable("adminId") Long id)
	{
		adminService.deleteAdmin(id);
	}
	
	
}
