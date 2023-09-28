package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.example.application.controllers.dto.AdminDTO;
import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.Admin;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;

@Mapper
public interface SportsBaseMap{

	
	@Mapping(source = "sportsBase.id", target = "id")
	@Mapping(source = "sportsBase.groundsList", target = "groundsList", qualifiedBy = GroundListToGroundListDTO.class)
	@Mapping(source = "sportsBase.admin", target = "admin", qualifiedBy = AdminToAdminDTO.class)
	public SportsBaseDTO toDTO(SportsBase sportsBase);
	
	
	@GroundListToGroundListDTO
	public default List<SportGroundDTO> mapGrounds(List<SportGround> list)
	{
		if(list == null)
		{
			return null;
		}
		
		List<SportGroundDTO> dtos = new ArrayList<SportGroundDTO>();
		
		for(SportGround ground : list)
		{
			SportGroundDTO sportGroundDTO = new SportGroundDTO();
			sportGroundDTO.setId(ground.getId());
			sportGroundDTO.setName(ground.getName());
			dtos.add(sportGroundDTO);
		}
		
		return dtos;
	}
	
	@AdminToAdminDTO
	public default AdminDTO mapAdmin(Admin admin)
	{
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setIdAdmin(admin.getIdAdmin());
		adminDTO.setEmail(admin.getEmail());
		return adminDTO;
	}
	 
}
