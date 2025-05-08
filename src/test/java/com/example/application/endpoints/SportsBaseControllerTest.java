package com.example.application.endpoints;

import com.example.application.controllers.AppointmentController;
import com.example.application.controllers.SportsBaseController;
import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.*;
import com.example.application.exceptions.SportsBaseException;
import com.example.application.services.AppointmentService;
import com.example.application.services.SportsBaseService;
import com.example.application.services.mappers.AdminMap;
import com.example.application.services.mappers.AppointmentMap;
import com.example.application.services.mappers.SportsBaseMap;
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
public class SportsBaseControllerTest {
    @Mock
    private SportsBaseService sportsBaseService;

    @Mock
    private AdminMap adminMap;


    @InjectMocks
    private SportsBaseController sportsBaseController;

    private SportsBase testSportsBase;
    private Admin testAdmin;

    @BeforeEach
    void setUp() {

        testSportsBase = new SportsBase();
        testSportsBase.setId(1L);
        testSportsBase.setName("Base");
        testSportsBase.setAddress("Street");
        testAdmin = new Admin();
        testAdmin.setName("Admin");
        testAdmin.setEmail("admin@gmail.com");
        testAdmin.setPassword("admin");
        testSportsBase.setAdmin(testAdmin);

    }

    @Test
    void getSportsBases() {
        List<SportsBase> sportsBases = new ArrayList<>();
        sportsBases.add(testSportsBase);
        when(sportsBaseService.getBases()).thenReturn(sportsBases);

        ResponseEntity<List<SportsBaseDTO>> response = sportsBaseController.getBases();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().stream().toList(),sportsBases.stream().map(sportsBase -> SportsBaseMap.toDTO(sportsBase)).toList());
        assertEquals(response.getBody().size(), 1);
    }

    @Test
    void addSportsBase() throws SportsBaseException {
        when(sportsBaseService.addNewBase(any(SportsBase.class))).thenReturn(testSportsBase);


        ResponseEntity<?> response = sportsBaseController.addBase(testSportsBase);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    void failGetSportsBase() {
        List<SportsBase> sportsBases = new ArrayList<>();
        when(sportsBaseService.getBases()).thenReturn(sportsBases);

        ResponseEntity<List<SportsBaseDTO>> response = sportsBaseController.getBases();
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(response.getBody(), null);
    }
}
