package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.application.exceptions.TrainerException;
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


    public List<Trainer> getAllTrainers() {
    	List<Trainer>  trainers = trainerRepository.findAll();

		return trainers;
    }

    public Trainer addTrainer(Trainer trainer) throws TrainerException {
    	Optional<Trainer> optionalTrainer = trainerRepository.findByName(trainer.getName());
	    if (optionalTrainer.isPresent()) {
	        throw new TrainerException("Trainer with this name already exists");
	    }
	    

	    return  trainerRepository.save(trainer);
    }

	public void deleteTrainer(Long id) throws TrainerException {
		boolean exists = trainerRepository.existsById(id);
	    if (!exists) {
	        throw new TrainerException("Trainer with id " + id + " does not exist");
	    }
	    trainerRepository.deleteById(id);
	}
   
}
