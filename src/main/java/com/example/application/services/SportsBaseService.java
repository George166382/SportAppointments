package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.application.exceptions.SportsBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.Admin;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;
import com.example.application.repositories.AdminRepository;
import com.example.application.repositories.SportsBaseRepository;
import com.example.application.services.mappers.SportsBaseMap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class SportsBaseService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private SportsBaseRepository sportsBaseRepository;
	@Autowired
	private SportsBaseMap baseMapper;
	

	
	public List<SportsBase> getBases() {
		List<SportsBase> sportsBaseList = sportsBaseRepository.findAll();

		return sportsBaseList;
	}

	public SportsBase addNewBase(SportsBase sportsBase) throws SportsBaseException {
		
		Optional<SportsBase> optionalSportsBase = sportsBaseRepository.findByAddress(sportsBase.getAddress());

		
		if (optionalSportsBase.isPresent()) {
		    throw new SportsBaseException("This base already exists");
		}

		 return sportsBaseRepository.save(sportsBase);
	}

	public void changeAdmin(String name, Long id) throws SportsBaseException{
		Optional<Admin> optionalAdmin = adminRepository.findByName(name);
		Optional<SportsBase> optionalSportsBase = sportsBaseRepository.findById(id);
		if(!optionalSportsBase.isPresent())
		{
			throw new SportsBaseException("Sports Base cannot be identified");
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

	public void deleteBase(Long id) throws SportsBaseException{
		Optional<SportsBase> optionalSportsBase = sportsBaseRepository.findById(id);
		if(!optionalSportsBase.isPresent())
		{
			throw new SportsBaseException("This sports base cannot be found");
		}
		sportsBaseRepository.deleteById(id);
	}
	
	
}
