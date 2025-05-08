package com.example.application.controllers;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.example.application.exceptions.AppointmentException;
import com.example.application.services.mappers.AppointmentMap;
import com.example.application.services.mappers.SportGroundMap;
import com.example.application.services.mappers.UserMap;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.AppointmentDTO2;
import com.example.application.entities.Appointment;
import com.example.application.entities.SportGround;
import com.example.application.entities.User;

import com.example.application.repositories.SportGroundRepository;
import com.example.application.repositories.UserRepository;
import com.example.application.services.AdminService;
import com.example.application.services.AppointmentService;
import com.example.application.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

    @Autowired
    private AppointmentMap appointmentMap;

    @Autowired
    private UserMap userMap;

    @Autowired
    private SportGroundMap sportGroundMap;

	@GetMapping 
	public ResponseEntity<List<AppointmentDTO>> getAppointments()
	{
            List<Appointment> appointments = appointmentService.getAppointments();
            if (appointments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
            for (Appointment appointment : appointments) {
                AppointmentDTO appointmentDTO = appointmentMap.toDTO(appointment);
                appointmentDTOs.add(appointmentDTO);
            }
            return new ResponseEntity<>(appointmentDTOs, HttpStatus.OK);

	}
	
	@PostMapping( consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addAppointment(@RequestBody Appointment appointment) {
        try {

            appointmentService.addAppointment(appointment);
            System.out.println(appointment.getUser().getName());
            return new ResponseEntity<>("Appointment added successfully", HttpStatus.CREATED);

        }
        catch (AppointmentException e) {

            if (e.getMessage().equals("Appointment with these dates already exists")) {
                return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);

        }
    }
	@DeleteMapping
	public ResponseEntity<String> deleteAppointment(@RequestParam(name="id") Long id)
	{
        try {
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<>("Sport ground deleted successfully", HttpStatus.NO_CONTENT);
        }
		catch (AppointmentException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
	}
}
