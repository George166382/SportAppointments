package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.AdminDTO;
import com.example.application.entities.Admin;
import com.example.application.entities.User;
import com.example.application.repositories.AdminRepository;
import com.example.application.services.mappers.AdminMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminMapper adminMapper;
	
	
	public List<AdminDTO> getAdmins() 
	 { 
		List<Admin> adminsList = adminRepository.findAll();
		List<AdminDTO> adminListDTO = new ArrayList<AdminDTO>();
		for(Admin admin: adminsList)
		{
			AdminDTO adminDTO = adminMapper.toDTO(admin);
			adminListDTO.add(adminDTO);
			
		}
		 return adminListDTO; 
	 }
	public void createAdmin(Admin admin) {
		
		Optional<Admin> obj = adminRepository.findByName(admin.getName());
		
		if(obj.isPresent())
		{
			throw new IllegalStateException("Already exists");
		}
		
		adminRepository.save(admin);
		
	}
	public void updateAdmin(String name, Long id) {
		// TODO Auto-generated method stub
		Optional<Admin> obj = adminRepository.findById(id);
		
		if(!obj.isPresent())
		{
			throw new IllegalStateException("Student not found");
		}
		
		obj = adminRepository.findByName(name);
		if(obj.isPresent())
		{
			throw new IllegalStateException("This name already exists");
		}
		
		adminRepository.updateAdmin(name,id);
		
	}
	public void deleteAdmin(Long id) {
		// TODO Auto-generated method stub
		Optional<Admin> obj = adminRepository.findById(id);
		
		if(!obj.isPresent())
		{
			throw new IllegalStateException("Student not found");
		}
		
		adminRepository.deleteById(id);
	}
	
}
