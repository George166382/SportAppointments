package com.example.application.services.mappers;

import com.example.application.controllers.dto.TrainerAvailabilityDTO;
import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.Trainer;
import com.example.application.entities.TrainerAvailability;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TrainerAvailabilityMap {
	@Mapping(source = "trainerAvailability.id", target = "id")
	@Mapping(source = "trainerAvailability.trainer", target = "trainer", qualifiedBy = TrainerToTrainerDTO.class)
    TrainerAvailabilityDTO toDTO(TrainerAvailability trainerAvailability);
	
	@TrainerToTrainerDTO
	public default TrainerDTO mapTrainer(Trainer trainer)
	{
		TrainerDTO trainerDTO = new TrainerDTO();
		trainerDTO.setId(trainer.getId());
		trainerDTO.setName(trainer.getName());
		
		return trainerDTO;
	}
}
