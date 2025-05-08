package com.example.application.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.application.exceptions.TrainerException;
import com.example.application.services.mappers.SportGroundMap;
import com.example.application.services.mappers.TrainerMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.Trainer;
import com.example.application.services.TrainerService;

@RestController
@RequestMapping("/api/v1/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;


    @GetMapping
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {

            List<Trainer> trainers = trainerService.getAllTrainers();

            if (trainers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<TrainerDTO> trainersDTOs = trainers.stream().map(trainer -> TrainerMap.toDTO(trainer)).toList();

            return new ResponseEntity<>(trainersDTOs, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> addTrainer(@RequestBody Trainer trainer) {
        try {
            trainerService.addTrainer(trainer);
            return new ResponseEntity<>("Trainer added successfully", HttpStatus.CREATED);
        } catch (TrainerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainer(@PathVariable Long id) {
        try {
            trainerService.deleteTrainer(id);
            return new ResponseEntity<>("Trainer deleted successfully", HttpStatus.NO_CONTENT);
        } catch (TrainerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}