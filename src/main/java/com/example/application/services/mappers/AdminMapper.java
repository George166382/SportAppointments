package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.AdminDTO;
import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.Admin;
import com.example.application.entities.SportsBase;

@Component
public class AdminMapper {
	@Autowired
	private SportsBaseMapper sportsBaseMapper;
	
	public AdminDTO toDTO(Admin admin)
	{
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setIdAdmin(admin.getIdAdmin());
		List<SportsBaseDTO> baseListDTO = new ArrayList<>();
		for(SportsBase base : admin.getBasesList())
		{
			baseListDTO.add(sportsBaseMapper.toDTO(base));
		}
		adminDTO.setBasesList(baseListDTO);
		return adminDTO;
	}
}
