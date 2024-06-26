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

import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.entities.SportGround;
import com.example.application.entities.User;
import com.example.application.services.AdminService;
import com.example.application.services.SportGroundService;

@RestController
@RequestMapping(path = "/api/v1/grounds")
public class SportGroundController {

	@Autowired
	private SportGroundService sportGroundService;
	
		
	 @GetMapping 
	  public List<SportGroundDTO> getSportGrounds() 
	  { 
		  return sportGroundService.getSportGrounds(); 
	  }
	 
	  @PostMapping
	  public void addSportGround(@RequestBody SportGround sportGround)
	  {
		  sportGroundService.addSportGround(sportGround);
	  }
	  @PutMapping
	  public void updateSportGround(@RequestParam(name="name")String name,@RequestParam(name="idSg")Long id)
	  {
		  sportGroundService.updateSportGround(name,id);
	  }
	  @DeleteMapping
	  public void deleteSportGround(@RequestParam(name="id") Long id)
	  {
		  sportGroundService.deleteSportGround(id);
	  }
	  
	
}
