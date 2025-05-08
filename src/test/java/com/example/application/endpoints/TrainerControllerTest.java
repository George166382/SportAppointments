package com.example.application.endpoints;

import com.example.application.controllers.TrainerController;
import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.Appointment;
import com.example.application.entities.SportGround;
import com.example.application.entities.Trainer;
import com.example.application.entities.User;
import com.example.application.exceptions.TrainerException;
import com.example.application.services.TrainerService;
import com.example.application.services.mappers.SportGroundMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainerControllerTest {

    @Mock
    private TrainerService trainerService;

    @Mock
    private SportGroundMap sportGroundMap;

    @InjectMocks
    private TrainerController trainerController;

    private Trainer testTrainer;
    private SportGround testSportGround;

    @BeforeEach
    void setUp() {
       testTrainer = new Trainer();
       testTrainer.setName("Trainer");
        testSportGround = new SportGround();
        testSportGround.setId(1L);
        testSportGround.setName("Test SportGround");
        testTrainer.setSportGround(testSportGround);
    }

    @Test
    void getTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(testTrainer);
        when(trainerService.getAllTrainers()).thenReturn(trainers);

        ResponseEntity<List<TrainerDTO>> response = trainerController.getAllTrainers();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().size(), 1);
    }

    @Test
    void addTrainer() throws TrainerException {
        when(trainerService.addTrainer(any(Trainer.class))).thenReturn(testTrainer);


        ResponseEntity<?> response = trainerController.addTrainer(testTrainer);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    void failGetTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        when(trainerService.getAllTrainers()).thenReturn(trainers);

        ResponseEntity<List<TrainerDTO>> response = trainerController.getAllTrainers();


        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(response.getBody(), null);
    }
}
