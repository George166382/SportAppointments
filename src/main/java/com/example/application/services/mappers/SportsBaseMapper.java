package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;

@Component
public class SportsBaseMapper {

	@Autowired
	private SportGroundMapper groundMapper;
	
		public SportsBaseDTO toDTO(SportsBase sportsBase)
		{
			SportsBaseDTO sportsBaseDTO = new SportsBaseDTO();
			sportsBaseDTO.setId(sportsBase.getId());
			List<SportGroundDTO> groundsListDTO = new ArrayList<>();
			for(SportGround sportGround : sportsBase.getGroundsList())
			{
				groundsListDTO.add(groundMapper.toDTO(sportGround));
			}
			sportsBaseDTO.setGroundsList(groundsListDTO);
			return sportsBaseDTO;
		}
}
