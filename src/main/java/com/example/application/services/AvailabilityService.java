package com.example.application.services;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.TrainerAvailabilityDTO;
import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.Appointment;
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
	
    
	public List<TrainerAvailability> getAllAvailabilities() {
    	List<TrainerAvailability>  availabilities = availabilityRepository.findAll();

		return availabilities;
    }

    public void addAvailability(TrainerAvailability availability) {
    	Optional<TrainerAvailability> optionalAvail = availabilityRepository.findByDateHour(availability.getAvailableDate(), availability.getAvailableHour());
		//System.out.println(optionalAvail.get().getId());
	    if (optionalAvail.isPresent()) {
	        throw new IllegalStateException("Availability already exists");
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

 
    public void deleteAvailability(Long id) {
        Optional<TrainerAvailability> optionalAvailability = availabilityRepository.findById(id);
		if(!optionalAvailability.isPresent())
		{
			throw new IllegalStateException("Availability does not exist");
		}
		availabilityRepository.deleteById(id);
    } 
}
