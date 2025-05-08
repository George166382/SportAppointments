package com.example.application.services.mappers;

import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.TrainerAvailabilityDTO;
import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.Trainer;
import com.example.application.entities.TrainerAvailability;

@Component
public class TrainerAvailabilityMap {

	public static  TrainerAvailabilityDTO toDTO(TrainerAvailability trainerAvailability) {
		if (trainerAvailability == null) {
			return null;
		}

		TrainerAvailabilityDTO trainerAvailabilityDTO = new TrainerAvailabilityDTO();
		trainerAvailabilityDTO.setId(trainerAvailability.getId());
		trainerAvailabilityDTO.setTrainer(mapTrainer(trainerAvailability.getTrainer()));
		return trainerAvailabilityDTO;
	}

	public static TrainerDTO mapTrainer(Trainer trainer) {
		if (trainer == null) {
			return null;
		}

		TrainerDTO trainerDTO = new TrainerDTO();
		trainerDTO.setId(trainer.getId());
		trainerDTO.setName(trainer.getName());
		return trainerDTO;
	}
}