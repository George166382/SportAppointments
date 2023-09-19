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

import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.SportsBase;
import com.example.application.services.AdminService;
import com.example.application.services.SportsBaseService;

@RestController
@RequestMapping
public class SportsBaseController {

	@Autowired
	private SportsBaseService sportsBaseService;
	
	
	@GetMapping(path="api/v1/sportsbase/getbases")
	public List<SportsBaseDTO> getBases()
	{
		return sportsBaseService.getBases();
	}
	
	@PostMapping(path="api/v1/sportsbase/postbases")
	public void addBase(@RequestBody SportsBase sportsBase)
	{
		sportsBaseService.addNewBase(sportsBase);
	}
	
	@PutMapping(path="api/v1/sportsbase/updateBaseAdmin")
	public void changeAdministratorOfTheBase(@RequestParam(name="adminName") String name, @RequestParam(name="idBase") Long id)
	{
		sportsBaseService.changeAdmin(name,id);
	}
	
	@DeleteMapping(path="api/v1/sportsbase/deletebase/{baseId}")
	public void deleteBase(@PathVariable("baseId") Long id)
	{
		sportsBaseService.deleteBase(id);
	}
}
