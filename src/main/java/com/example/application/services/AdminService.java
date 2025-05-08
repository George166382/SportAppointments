package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.example.application.exceptions.AdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.AdminDTO;
import com.example.application.entities.Admin;
import com.example.application.entities.User;
import com.example.application.repositories.AdminRepository;
import com.example.application.services.mappers.AdminMap;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class AdminService {

	private AdminRepository adminRepository;
	private AdminMap mapper;

	@Autowired
	public AdminService (AdminRepository adminRepository, AdminMap mapper) {
		this.adminRepository = adminRepository;
		this.mapper = mapper;
	}
	
	public List<Admin> getAdmins()
	 { 
		List<Admin> adminsList = adminRepository.findAll();

		 return adminsList;
	 }
	public void createAdmin(Admin admin) throws AdminException {
		
		Optional<Admin> obj = adminRepository.findByName(admin.getName());
		
		if(obj.isPresent())
		{
			throw new AdminException("Already exists");
		}
		
		adminRepository.save(admin);
		
	}
	public void updateAdmin(String name, Long id) throws AdminException {

		Optional<Admin> obj = adminRepository.findById(id);
		
		if(!obj.isPresent())
		{
			throw new AdminException("Admin not found");
		}
		
		obj = adminRepository.findByName(name);
		if(obj.isPresent())
		{
			throw new AdminException("This name already exists");
		}
		
		adminRepository.updateAdmin(name,id);
		
	}
	public void deleteAdmin(Long id) throws AdminException{

		Optional<Admin> obj = adminRepository.findById(id);
		
		if(!obj.isPresent())
		{
			throw new AdminException("Admin not found");
		}
		
		adminRepository.deleteById(id);
	}
	
}
