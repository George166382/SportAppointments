package com.example.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.Trainer;
import com.example.application.entities.TrainerAvailability;
import com.example.application.services.AvailabilityService;
import com.example.application.services.TrainerService;

@RestController
@RequestMapping("/api/v1/availability")
public class TrainerAvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping
    public List<TrainerAvailabilityDTO> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities();
    }
    
    
    @PostMapping
    public void addAvailability(@RequestBody TrainerAvailability availability) {
        availabilityService.addAvailability(availability);
    }

 /*   @PutMapping("/{id}")
    public void updateTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        availabilityService.updateTrainer(id, trainer);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainer(@PathVariable Long id) {
        availabilityService.deleteTrainer(id);
    } */
}
