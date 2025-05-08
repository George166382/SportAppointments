package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.TrainerAvailabilityDTO;
import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.SportGround;
import com.example.application.entities.Trainer;
import com.example.application.entities.TrainerAvailability;

@Component
public class TrainerMap {

	public static TrainerDTO toDTO(Trainer trainer) {
		if (trainer == null) {
			return null;
		}

		TrainerDTO trainerDTO = new TrainerDTO();
		trainerDTO.setId(trainer.getId());
		trainerDTO.setName(trainer.getName());
		trainerDTO.setSportGround(mapSportGround(trainer.getSportGround()));
		trainerDTO.setAvailabilityList(mapList(trainer.getAvailabilityList()));
		return trainerDTO;
	}

	public static SportGroundDTO mapSportGround(SportGround sportGround) {
		if (sportGround == null) {
			return null;
		}

		SportGroundDTO sportGroundDTO = new SportGroundDTO();
		sportGroundDTO.setId(sportGround.getId());
		sportGroundDTO.setName(sportGround.getName());
		sportGroundDTO.setCapacity(sportGround.getCapacity());
		return sportGroundDTO;
	}

	public static List<TrainerAvailabilityDTO> mapList(List<TrainerAvailability> list) {
		if (list == null) {
			return null;
		}

		List<TrainerAvailabilityDTO> dtos = new ArrayList<>();
		for (TrainerAvailability av : list) {
			TrainerAvailabilityDTO avDTO = new TrainerAvailabilityDTO();
			avDTO.setId(av.getId());
			avDTO.setAvailableDate(av.getAvailableDate());
			avDTO.setAvailableHour(av.getAvailableHour());
			dtos.add(avDTO);
		}
		return dtos;
	}
}