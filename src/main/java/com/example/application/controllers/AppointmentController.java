package com.example.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.entities.Appointment;
import com.example.application.services.AdminService;
import com.example.application.services.AppointmentService;

@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	
	
	@GetMapping(path="api/v1/appointments/getappointments")
	public List<AppointmentDTO> getAppointments()
	{
		return appointmentService.getAppointments();
	}
	
	@PostMapping(path="api/v1/appointments/getappointments")
	public void addAppointment(@RequestBody Appointment appointment)
	{
		appointmentService.addAppointment(appointment);
	}
	
	@DeleteMapping(path="api/v1/appointments/getappointments/{id}")
	public void deleteAppointment(@PathVariable("id") Long id)
	{
		appointmentService.deleteAppointment(id);
	}
}
