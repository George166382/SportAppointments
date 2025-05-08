package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.AdminDTO;
import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.Admin;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;

@Component
public class SportsBaseMap {

	public static SportsBaseDTO toDTO(SportsBase sportsBase) {
		if (sportsBase == null) {
			return null;
		}

		SportsBaseDTO sportsBaseDTO = new SportsBaseDTO();
		sportsBaseDTO.setId(sportsBase.getId());
		sportsBaseDTO.setGroundsList(mapGrounds(sportsBase.getGroundsList()));
		sportsBaseDTO.setAdmin(mapAdmin(sportsBase.getAdmin()));
		return sportsBaseDTO;
	}

	public static List<SportGroundDTO> mapGrounds(List<SportGround> list) {
		if (list == null) {
			return null;
		}

		List<SportGroundDTO> dtos = new ArrayList<>();
		for (SportGround ground : list) {
			SportGroundDTO sportGroundDTO = new SportGroundDTO();
			sportGroundDTO.setId(ground.getId());
			sportGroundDTO.setName(ground.getName());
			dtos.add(sportGroundDTO);
		}
		return dtos;
	}

	public static AdminDTO mapAdmin(Admin admin) {
		if (admin == null) {
			return null;
		}

		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setIdAdmin(admin.getIdAdmin());
		adminDTO.setEmail(admin.getEmail());
		adminDTO.setName(admin.getName());
		return adminDTO;
	}
}