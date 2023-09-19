package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;
import com.example.application.entities.User;
import com.example.application.repositories.SportGroundRepository;
import com.example.application.repositories.SportsBaseRepository;
import com.example.application.services.mappers.SportGroundMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class SportGroundService {

	@Autowired
	private SportGroundRepository sportGroundRepository;
	@Autowired
	private SportsBaseRepository sportsBaseRepository;
	@Autowired
	private SportGroundMapper groundMapper;
	
	
	public List<SportGroundDTO> getSportGrounds() {
		List<SportGround> grounds = sportGroundRepository.findAll();
		List<SportGroundDTO> groundsListDTO = new ArrayList<>();
		for(SportGround sportGround : grounds)
		{
			SportGroundDTO groundDTO = groundMapper.toDTO(sportGround);
			groundsListDTO.add(groundDTO);
		}
		return groundsListDTO;
	}

	public void addSportGround(SportGround sportGround) {
	    Optional<SportGround> optionalSportGround = sportGroundRepository.findByName(sportGround.getName());
	    if (optionalSportGround.isPresent()) {
	        throw new IllegalStateException("Sport ground with this name already exists");
	    }

	    SportsBase sb = sportGround.getSportsBase();
	    if(sb == null)
	    {
	    	throw new IllegalStateException("Sports Base is not specified");
	    }
	    
	    Optional<SportsBase> optionalSb = sportsBaseRepository.findById(sb.getId());
	    if(!optionalSb.isPresent())
	    {
	    	throw new IllegalStateException("Sports Base does not exist");
	    }
	    
	    sb = optionalSb.get();
	    List<SportGround> groundsList = sb.getGroundsList();

	    if (groundsList == null) {
	        groundsList = new ArrayList<>(); 
	        sb.setGroundsList(groundsList);
	    }

	    groundsList.add(sportGround);
	    sb.setGroundsList(groundsList);
	    sportsBaseRepository.save(sb);
	}

	public void updateSportGround(String name, Long id) {
		Optional<SportGround> optionalSportGround = sportGroundRepository.findById(id);
		if(!optionalSportGround.isPresent())
		{
			throw new IllegalStateException("Sport ground not found");
		}
		SportsBase sb = optionalSportGround.get().getSportsBase();
		sb.getGroundsList().remove(optionalSportGround.get());
		SportGround sg = optionalSportGround.get();
		sg.setName(name);
		sb.getGroundsList().add(sg);
		sportsBaseRepository.save(sb);
		
	}

	public void deleteSportGround(Long id) {
		Optional<SportGround> optionalSportGround = sportGroundRepository.findById(id);
		if(!optionalSportGround.isPresent())
		{
			throw new IllegalStateException("This sport ground not found");
		}
		sportGroundRepository.deleteById(id);
	}
}
