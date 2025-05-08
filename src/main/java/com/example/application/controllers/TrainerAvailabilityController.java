package com.example.application.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.controllers.dto.TrainerAvailabilityDTO;
import com.example.application.entities.TrainerAvailability;
import com.example.application.services.AvailabilityService;

@RestController
@RequestMapping("/api/v1/availability")
public class TrainerAvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private TrainerMap trainerMap;

    @GetMapping
    public ResponseEntity<List<TrainerAvailabilityDTO>> getAllAvailabilities() {

            List<TrainerAvailability> availabilities = availabilityService.getAllAvailabilities();
            if (availabilities.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<TrainerAvailabilityDTO> availabilityDTOS = new ArrayList<>();
            for (TrainerAvailability availability : availabilities)
            {
                TrainerAvailabilityDTO availabilityDTO = new TrainerAvailabilityDTO();
                availabilityDTO.setAvailableHour(availability.getAvailableHour());
                availabilityDTO.setAvailableDate(availability.getAvailableDate());
                availabilityDTO.setTrainer(trainerMap.toDTO(availability.getTrainer()));
                availabilityDTOS.add(availabilityDTO);
            }

            return new ResponseEntity<>(availabilityDTOS, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> addAvailability(@RequestBody TrainerAvailability availability) {
        try {
            availabilityService.addAvailability(availability);
            return new ResponseEntity<>("Availability added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAvailability(@PathVariable Long id) {
        try {
            availabilityService.deleteAvailability(id);
            return new ResponseEntity<>("Availability deleted successfully", HttpStatus.NO_CONTENT);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
