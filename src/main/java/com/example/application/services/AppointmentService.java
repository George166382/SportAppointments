package com.example.application.services;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.example.application.exceptions.AppointmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.AppointmentDTO2;
import com.example.application.entities.Appointment;
import com.example.application.entities.SportGround;
import com.example.application.entities.User;

import com.example.application.repositories.AppointmentRepository;
import com.example.application.repositories.SportGroundRepository;
import com.example.application.repositories.UserRepository;
import com.example.application.services.mappers.AppointmentMap;


@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private AppointmentMap appointmentMapper;
	
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private SportGroundRepository grRep;
	
	@Autowired
	private Appointment createdAppointment;
	

	
	public Appointment getAppointment() {
		return this.createdAppointment;
	}

	public List<Appointment> getAppointments()
	{
		List<Appointment> appointments = appointmentRepository.findAll();

		return appointments;
	}

	public Appointment addAppointment(Appointment appointment) throws AppointmentException {
		Optional<Appointment> optionalAppointment = appointmentRepository.findByDateHour(appointment.getAppointmentDate(), appointment.getAppointmentHour());
		if(optionalAppointment.isPresent())
		{
			if(appointment.getTrainerName().equals("") && appointment.getTrainerName().equals(optionalAppointment.get().getTrainerName()))
			{
				throw new AppointmentException("Appointment with these dates already exists");
			}
			else
			{
				if(appointment.getUser().getName().equals(optionalAppointment.get().getUser().getName()))
				{
					throw new AppointmentException("You already checked this date");
				}
				
		        long appointmentCount = appointmentRepository.countByDateHour(appointment.getAppointmentDate(), appointment.getAppointmentHour());
		        if(appointmentCount == appointment.getSportGround().getCapacity()) {
		            throw new AppointmentException("Maximum number of appointments reached for this date and hour");
		        }
			}
			
		}
		return appointmentRepository.save(appointment);
	}

	public void deleteAppointment(Long id) throws AppointmentException{
		if (!appointmentRepository.existsById(id)) {
			throw new AppointmentException("Appointment with id " + id + " not found");
		}
		appointmentRepository.deleteById(id);
	}
}



