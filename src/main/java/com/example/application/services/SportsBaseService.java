package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.Admin;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;
import com.example.application.repositories.AdminRepository;
import com.example.application.repositories.SportsBaseRepository;
import com.example.application.services.mappers.SportsBaseMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class SportsBaseService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private SportsBaseRepository sportsBaseRepository;
	@Autowired
	private SportsBaseMapper baseMapper;
	

	
	public List<SportsBaseDTO> getBases() {
		List<SportsBase> sportsBaseList = sportsBaseRepository.findAll();
		List<SportsBaseDTO> sportsBaseListDTO = new ArrayList<>();
		for(SportsBase sportsBase : sportsBaseList)
		{
			SportsBaseDTO baseDTO = baseMapper.toDTO(sportsBase);
			sportsBaseListDTO.add(baseDTO);
		}
		return sportsBaseListDTO;
	}

	public void addNewBase(SportsBase sportsBase) {
		
		Optional<SportsBase> optionalSportsBase = sportsBaseRepository.findByAddress(sportsBase.getAddress());

		// Check if sportsBase is already present
		if (optionalSportsBase.isPresent()) {
		    throw new IllegalStateException("This base already exists");
		}

		// Check if admin is not null
		Admin admin = sportsBase.getAdmin();
		if (admin == null) {
		    throw new IllegalStateException("Admin is not specified");
		}

		Optional<Admin> optionalAdmin = adminRepository.findById(admin.getIdAdmin());

		// Check if admin exists
		if (!optionalAdmin.isPresent()) {
		    throw new IllegalStateException("This admin doesn't exist");
		}

		 admin = optionalAdmin.get();
		 List<SportsBase> basesList = admin.getBasesList();

		    if (basesList == null) {
		        basesList = new ArrayList<>(); 
		        admin.setBasesList(basesList);;
		    }

		    basesList.add(sportsBase);
		    admin.setBasesList(basesList);
		 adminRepository.save(admin);
	}

	public void changeAdmin(String name, Long id) {
		Optional<Admin> optionalAdmin = adminRepository.findByName(name);
		Optional<SportsBase> optionalSportsBase = sportsBaseRepository.findById(id);
		if(!optionalAdmin.isPresent()||!optionalSportsBase.isPresent())
		{
			throw new IllegalStateException("This administrator is not in DB or the Sports Base cannot be identified");
		}
		SportsBase sBase = optionalSportsBase.get();
		sBase.getAdmin().getBasesList().remove(sBase);
		List<SportsBase> bases = optionalAdmin.get().getBasesList();
		if(bases == null)
		{
			bases = new ArrayList<>();
		}
		bases.add(sBase);
		optionalAdmin.get().setBasesList(bases);
		sBase.setAdmin(optionalAdmin.get());
		adminRepository.save(sBase.getAdmin());
	}

	public void deleteBase(Long id) {
		Optional<SportsBase> optionalSportsBase = sportsBaseRepository.findById(id);
		if(!optionalSportsBase.isPresent())
		{
			throw new IllegalStateException("This sports base cannot be found");
		}
		sportsBaseRepository.deleteById(id);
	}
	
	
}
