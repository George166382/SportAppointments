package com.example.application.endpoints;

import com.example.application.controllers.AppointmentController;
import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.UserDTO;
import com.example.application.entities.Appointment;
import com.example.application.entities.SportGround;
import com.example.application.entities.User;
import com.example.application.exceptions.AppointmentException;
import com.example.application.services.AppointmentService;
import com.example.application.services.SportGroundService;
import com.example.application.services.UserService;
import com.example.application.services.mappers.AppointmentMap;
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
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private AppointmentMap appointmentMap;


    @InjectMocks
    private AppointmentController appointmentController;

    private Appointment testAppointment;
    private User testUser;
    private SportGround testSportGround;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setIdUser(1L);
        testUser.setName("Test User");
        testUser.setEmail("test@gmail.com");
        testUser.setPassword("test");

        testSportGround = new SportGround();
        testSportGround.setId(1L);
        testSportGround.setName("Test SportGround");

        testAppointment = new Appointment();
        testAppointment.setId(1L);
        testAppointment.setUser(testUser);
        testAppointment.setSportGround(testSportGround);
        testAppointment.setAppointmentDate(Date.valueOf("2022-12-12"));
        testAppointment.setAppointmentHour(Time.valueOf("12:00:00"));
    }

    @Test
    void getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(testAppointment);
        when(appointmentService.getAppointments()).thenReturn(appointments);

        ResponseEntity<List<AppointmentDTO>> response = appointmentController.getAppointments();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().size(), 1);
    }

    @Test
    void addAppointment() throws AppointmentException {
        when(appointmentService.addAppointment(any(Appointment.class))).thenReturn(testAppointment);


        ResponseEntity<?> response = appointmentController.addAppointment(testAppointment);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    void failGetAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        when(appointmentService.getAppointments()).thenReturn(appointments);

        ResponseEntity<List<AppointmentDTO>> response = appointmentController.getAppointments();
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(response.getBody(), null);
    }

}
