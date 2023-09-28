package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.entities.Appointment;
import com.example.application.repositories.AppointmentRepository;
import com.example.application.services.mappers.AppointmentMap;


@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private AppointmentMap appointmentMapper;
	

	public List<AppointmentDTO> getAppointments()
	{
		List<Appointment> appointments = appointmentRepository.findAll();
		List<AppointmentDTO> appointmentsListDTO = new ArrayList<>();
		for(Appointment appointment : appointments)
		{
			AppointmentDTO appointmentDTO = appointmentMapper.toDTO(appointment);
			appointmentsListDTO.add(appointmentDTO);
		}
		return appointmentsListDTO;
	}

	public void addAppointment(Appointment appointment) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findByDateHour(appointment.getAppointmentDate(), appointment.getAppointmentHour());
		if(optionalAppointment.isPresent())
		{
			throw new IllegalStateException("Appointment with these dates already exists");
		}
		appointmentRepository.save(appointment);
	}

	public void deleteAppointment(Long id) {
		Optional<Appointment> optionalAppointemnt = appointmentRepository.findById(id);
		if(!optionalAppointemnt.isPresent())
		{
			throw new IllegalStateException("Appointment does not exist");
		}
		appointmentRepository.deleteById(id);
	}
}
