package com.example.application.controllers;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
import com.example.application.exceptions.SportGroundNotFoundException;
import com.example.application.exceptions.UserNotFoundException;
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

	@GetMapping 
	public List<AppointmentDTO> getAppointments()
	{
		return appointmentService.getAppointments();
	}
	
	@PostMapping( consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentDTO2 appointmentDTO) {
        try {
            appointmentService.createAppointment(appointmentDTO);
            Appointment appointment = appointmentService.getAppointment();
            appointmentService.addAppointment(appointment);
            System.out.println(appointment.getUser().getName());
            return ResponseEntity.ok("Appointment created successfully");

        }
        catch (UserNotFoundException e) {
            // Log the stack trace for debugging; remove before going to production for security
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        catch (SportGroundNotFoundException e) {
            // Log the stack trace for debugging; remove before going to production for security
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sport ground not found");
        }
        catch (Exception e) {
            // Log the stack trace for debugging; remove before going to production for security
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
        }
    }
	@DeleteMapping
	public void deleteAppointment(@RequestParam(name="id") Long id)
	{
		appointmentService.deleteAppointment(id);
	}
}
