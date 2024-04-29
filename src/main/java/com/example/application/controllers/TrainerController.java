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
    public List<TrainerDTO> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @PostMapping
    public void addTrainer(@RequestBody Trainer trainer) {
        trainerService.addTrainer(trainer);
    }

    @PutMapping("/{id}")
    public void updateTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        trainerService.updateTrainer(id, trainer);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
    }
}
