package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.application.exceptions.SportGroundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;
import com.example.application.entities.User;
import com.example.application.repositories.SportGroundRepository;
import com.example.application.repositories.SportsBaseRepository;
import com.example.application.services.mappers.SportGroundMap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class SportGroundService {

	@Autowired
	private SportGroundRepository sportGroundRepository;
	@Autowired
	private SportsBaseRepository sportsBaseRepository;
	@Autowired
	private SportGroundMap groundMapper;
	
	
	public List<SportGround> getSportGrounds() {
		List<SportGround> grounds = sportGroundRepository.findAll();

		return grounds;
	}

	public SportGround getSportGround(Long id) {
		Optional<SportGround> optionalSportGround = sportGroundRepository.findById(id);
		if(!optionalSportGround.isPresent())
		{
			throw new IllegalStateException("This sport ground does not exist");
		}
		SportGround sportGround = optionalSportGround.get();

		return sportGround;
	}

	public SportGround addSportGround(SportGround sportGround) throws SportGroundException{
	    Optional<SportGround> optionalSportGround = sportGroundRepository.findByName(sportGround.getName());
	    if (optionalSportGround.isPresent() && sportGround.getName().equals(optionalSportGround.get().getName()))  {
	        throw new SportGroundException("Sport ground with this name already exists");
	    }

	    

	    return sportGroundRepository.save(sportGround);
	}

	public SportGround updateSportGround(String name, Long id) throws SportGroundException{
		Optional<SportGround> optionalSportGround = sportGroundRepository.findById(id);
		if(!optionalSportGround.isPresent())
		{
			throw new SportGroundException("Sport ground not found");
		}

		SportGround sg = optionalSportGround.get();
		sg.setName(name);
		return sportGroundRepository.save(sg);
		
	}

	public void deleteSportGround(Long id) throws SportGroundException {
		Optional<SportGround> optionalSportGround = sportGroundRepository.findById(id);
		if(!optionalSportGround.isPresent())
		{
			throw new SportGroundException("This sport ground not found");
		}
		sportGroundRepository.deleteById(id);
	}
}
