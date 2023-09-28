package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.AdminDTO;
import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.Admin;
import com.example.application.entities.SportsBase;
import com.fasterxml.jackson.databind.util.Converter;

@Mapper
public interface AdminMap {
	
	AdminMap INSTANCE = Mappers.getMapper(AdminMap.class);
	
	@Mapping(source = "admin.idAdmin",target = "idAdmin")
	@Mapping(source = "admin.basesList",target = "basesList", qualifiedBy = BaseListToBaseListDTO.class)

	public AdminDTO toDTO(Admin admin);
	
	
	
	  @BaseListToBaseListDTO public default List<SportsBaseDTO>
	  map(List<SportsBase> list) { if (list == null) { return null; }
	  
	  List<SportsBaseDTO> result = new ArrayList<>();
	  
	  for (SportsBase sportsBase : list) { 
		  SportsBaseDTO sportsBaseDTO = new SportsBaseDTO(); 
		  sportsBaseDTO.setId(sportsBase.getId());
		  sportsBaseDTO.setAddress(sportsBase.getAddress());
		  sportsBaseDTO.setName(sportsBase.getName());
		 // SportsBaseDTO sportsBaseDTO = SportsBaseMap.INSTANCE.toDTO(sportsBase);
		  result.add(sportsBaseDTO); 
}
	  
	  return result; }
	 
	 
}


