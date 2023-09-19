package com.example.application.services.mappers;

import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.entities.Appointment;

@Component
public class AppointmentMapper {

	
	
	public AppointmentDTO toDTO(Appointment appointment)
	{
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setId(appointmentDTO.getId());
		return appointmentDTO;
	}
}
