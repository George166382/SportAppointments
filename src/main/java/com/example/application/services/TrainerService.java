package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;
import com.example.application.entities.Trainer;
import com.example.application.repositories.SportGroundRepository;
import com.example.application.repositories.TrainerRepository;
import com.example.application.services.mappers.TrainerMap;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;
    
    @Autowired
    private SportGroundRepository sportGroundRepository;
    
    @Autowired
    private TrainerMap trainerMapper;


    public List<TrainerDTO> getAllTrainers() {
    	List<Trainer>  trainers = trainerRepository.findAll();
		List<TrainerDTO> trainersListDTO = new ArrayList<>();
		for(Trainer trainer : trainers)
		{
			TrainerDTO trainerDTO = trainerMapper.toDTO(trainer);
			trainersListDTO.add(trainerDTO);
		}
		return trainersListDTO;
    }

    public void addTrainer(Trainer trainer) {
    	Optional<Trainer> optionalTrainer = trainerRepository.findByName(trainer.getName());
	    if (optionalTrainer.isPresent()) {
	        throw new IllegalStateException("Trainer with this name already exists");
	    }

	    SportGround sg = trainer.getSportGround();
	    if(sg == null)
	    {
	    	throw new IllegalStateException("Sport Ground is not specified");
	    }
	    
	    Optional<SportGround> optionalSg = sportGroundRepository.findById(sg.getId());
	    if(!optionalSg.isPresent())
	    {
	    	throw new IllegalStateException("Sport Ground does not exist");
	    }
	    
	    sg = optionalSg.get();
	    List<Trainer> trainersList = sg.getTrainersList();

	    if (trainersList == null) {
	        trainersList = new ArrayList<>(); 
	        sg.setTrainersList(trainersList);;
	    }

	    trainersList.add(trainer);
	    sg.setTrainersList(trainersList);
	    sportGroundRepository.save(sg);
    }

    public void updateTrainer(Long id, Trainer trainer) {
        // Implement logic to update an existing trainer
    }

    public void deleteTrainer(Long id) {
        // Implement logic to delete a trainer
    }
}
