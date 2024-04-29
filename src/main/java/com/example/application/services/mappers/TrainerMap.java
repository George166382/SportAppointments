package com.example.application.services.mappers;

import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.TrainerAvailabilityDTO;
import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.SportGround;
import com.example.application.entities.Trainer;
import com.example.application.entities.TrainerAvailability;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TrainerMap {

	@Mapping(source = "trainer.id", target = "id")
	@Mapping(source = "trainer.name", target = "name")
	@Mapping(source = "trainer.sportGround", target = "sportGround", qualifiedBy = SportGroundToSportGroundDTO.class)
	@Mapping(source = "trainer.availabilityList", target = "availabilityList", qualifiedBy = AvailabilityListToAvailabilityListDTO.class)
    TrainerDTO toDTO(Trainer trainer);
	
	@SportGroundToSportGroundDTO
	public default SportGroundDTO mapSportGround(SportGround sportGround)
	{
		SportGroundDTO sportGroundDTO = new SportGroundDTO();
		sportGroundDTO.setId(sportGround.getId());
		sportGroundDTO.setName(sportGround.getName());
		sportGroundDTO.setCapacity(sportGround.getCapacity());
		return sportGroundDTO;
	}
	
	@AvailabilityListToAvailabilityListDTO
	public default List<TrainerAvailabilityDTO> mapList(List<TrainerAvailability> list)
	{
		if(list == null)
		{
			return null;
		}
		
		List<TrainerAvailabilityDTO> dtos = new ArrayList<>();
		for(TrainerAvailability av : list)
		{
			TrainerAvailabilityDTO avDTO = new TrainerAvailabilityDTO();
			avDTO.setId(av.getId());
			avDTO.setAvailableDate(av.getAvailableDate());
			avDTO.setAvailableHour(av.getAvailableHour());
			dtos.add(avDTO);
		}
		return dtos;
	}
}
