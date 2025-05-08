package com.example.application.endpoints;

import com.example.application.controllers.SportGroundController;
import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.Admin;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;
import com.example.application.exceptions.SportGroundException;
import com.example.application.services.SportGroundService;
import com.example.application.services.mappers.SportsBaseMap;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SportsGroundControllerTest {

    @Mock
    private SportGroundService sportGroundService;

    @Mock
    private SportsBaseMap sportsBaseMap;

    @InjectMocks
    private SportGroundController sportGroundController;

    private SportGround testSportGround;
    private SportsBase testSportsBase;

    @BeforeEach
    void setUp() {
        testSportGround = new SportGround();
        testSportGround.setName("Football Ground");
        testSportGround.setCapacity(10L);
        testSportsBase = new SportsBase();
        testSportsBase.setId(1L);
        testSportsBase.setName("Base");
        testSportsBase.setAddress("Street");
        testSportGround.setSportsBase(testSportsBase);

    }

    @Test
    void getSportGrounds() {
        List<SportGround> sportGrounds = new ArrayList<>();
        sportGrounds.add(testSportGround);
        when(sportGroundService.getSportGrounds()).thenReturn(sportGrounds);

        ResponseEntity<List<SportGroundDTO>> response = sportGroundController.getSportGrounds();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().size(), 1);
    }

    @Test
    void addSportGround() throws SportGroundException {
        when(sportGroundService.addSportGround(any(SportGround.class))).thenReturn(testSportGround);


        ResponseEntity<?> response = sportGroundController.addSportGround(testSportGround);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }



    @Test
    void failAddSportGround() throws SportGroundException {
        // Simulate a conflict in the service layer
        when(sportGroundService.addSportGround(any(SportGround.class)))
                .thenThrow(new SportGroundException("Sport ground with this name already exists"));

        // Call the controller method
        ResponseEntity<?> response = sportGroundController.addSportGround(testSportGround);

        // Verify the response status code
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void failGetSportGround() {
        List<SportGround> sportGrounds = new ArrayList<>();
        when(sportGroundService.getSportGrounds()).thenReturn(sportGrounds);

        ResponseEntity<List<SportGroundDTO>> response = sportGroundController.getSportGrounds();

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(response.getBody(), null);
    }
}
