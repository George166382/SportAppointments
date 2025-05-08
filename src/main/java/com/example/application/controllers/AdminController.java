package com.example.application.controllers;

import java.util.List;

import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.exceptions.AdminException;
import com.example.application.exceptions.TrainerException;
import com.example.application.services.mappers.AdminMap;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.application.services.AdminService;




@RestController 
@RequestMapping(path = "/api/v1/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;
	

	 
	@GetMapping
	  public ResponseEntity<List<AdminDTO>> getAdmins()
	  { 
		  List<Admin> admins = adminService.getAdmins();
		  if (admins.isEmpty())
		  {
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		  List<AdminDTO> adminDTOS = admins.stream().map(admin -> AdminMap.toDTO(admin)).toList();
		  return  new ResponseEntity<>(adminDTOS,HttpStatus.OK);
	  }
	
	@PostMapping
	 public ResponseEntity<String> createAdmin(@RequestBody Admin admin)
	 {
		 try {
			 adminService.createAdmin(admin);
			 return new ResponseEntity<>("Admin added successfully",HttpStatus.CREATED);
		 }
		 catch (AdminException e) {
			 return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		 }

	 }
	
	@PutMapping
	public ResponseEntity<String> updateAdmin(@RequestParam(name="name") String name, @RequestParam(name="id") Long id)
	{
		try {
			adminService.updateAdmin(name,id);
			return new ResponseEntity<>("Admin updated",HttpStatus.CREATED);
		}
		catch (AdminException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteAdmin(@RequestParam(name="id") Long id)
	{
		try {
			adminService.deleteAdmin(id);
			return new ResponseEntity<>("Trainer deleted successfully", HttpStatus.NO_CONTENT);
		} catch (AdminException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
}
