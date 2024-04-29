package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.TrainerAvailabilityDTO;
import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.SportGround;
import com.example.application.entities.Trainer;
import com.example.application.entities.TrainerAvailability;
import com.example.application.repositories.AvailabilityRepository;
import com.example.application.repositories.SportGroundRepository;
import com.example.application.repositories.TrainerRepository;
import com.example.application.services.mappers.TrainerAvailabilityMap;
import com.example.application.services.mappers.TrainerMap;

@Service
public class AvailabilityService {
	@Autowired
    private AvailabilityRepository availabilityRepository;
    
    @Autowired
    private TrainerRepository trainerRepository;
    
    @Autowired
    private TrainerAvailabilityMap availabilityMapper;
	
    
	public List<TrainerAvailabilityDTO> getAllAvailabilities() {
    	List<TrainerAvailability>  availabilities = availabilityRepository.findAll();
		List<TrainerAvailabilityDTO> availabilityDTOs = new ArrayList<>();
		for(TrainerAvailability availability : availabilities)
		{
			TrainerAvailabilityDTO availabilityDTO = availabilityMapper.toDTO(availability);
			availabilityDTOs.add(availabilityDTO);
		}
		return availabilityDTOs;
    }

    public void addAvailability(TrainerAvailability availability) {
    	Optional<TrainerAvailability> optionalAvail = availabilityRepository.findById(availability.getId());
	    if (optionalAvail.isPresent()) {
	        throw new IllegalStateException("Trainer with this name already exists");
	    }

	    Trainer tr = availability.getTrainer();
	    if(tr == null)
	    {
	    	throw new IllegalStateException("Trainer is not specified");
	    }
	    
	    Optional<Trainer> optionalTr = trainerRepository.findById(tr.getId());
	    if(!optionalTr.isPresent())
	    {
	    	throw new IllegalStateException("Sport Ground does not exist");
	    }
	    
	    tr = optionalTr.get();
	    List<TrainerAvailability> availList = tr.getAvailabilityList();

	    if (availList == null) {
	        availList = new ArrayList<>(); 
	        tr.setAvailabilityList(availList);
	    }

	    availList.add(availability);
	    tr.setAvailabilityList(availList);;
	    trainerRepository.save(tr);
    }

 /*   public void updateTrainer(Long id, Trainer trainer) {
        // Implement logic to update an existing trainer
    }

    public void deleteTrainer(Long id) {
        // Implement logic to delete a trainer
    } */
}
