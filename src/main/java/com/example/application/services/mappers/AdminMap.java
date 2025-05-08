package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.AdminDTO;
import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.Admin;
import com.example.application.entities.SportsBase;

@Component
public class AdminMap {

	public static AdminDTO toDTO(Admin admin) {
		if (admin == null) {
			return null;
		}

		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setIdAdmin(admin.getIdAdmin());
		adminDTO.setBasesList(map(admin.getBasesList()));
		return adminDTO;
	}

	public static List<SportsBaseDTO> map(List<SportsBase> list) {
		if (list == null) {
			return null;
		}

		List<SportsBaseDTO> result = new ArrayList<>();
		for (SportsBase sportsBase : list) {
			SportsBaseDTO sportsBaseDTO = new SportsBaseDTO();
			sportsBaseDTO.setId(sportsBase.getId());
			sportsBaseDTO.setAddress(sportsBase.getAddress());
			sportsBaseDTO.setName(sportsBase.getName());
			result.add(sportsBaseDTO);
		}
		return result;
	}
}