package com.example.application.services;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.AppointmentDTO2;
import com.example.application.entities.Appointment;
import com.example.application.entities.SportGround;
import com.example.application.entities.User;
import com.example.application.exceptions.SportGroundNotFoundException;
import com.example.application.exceptions.UserNotFoundException;
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
	

	
	public void createAppointment(AppointmentDTO2 appointmentDTO) throws UserNotFoundException, SportGroundNotFoundException {
	    // Parse the date and hour from the input DTO
	    // Create and populate Appointment entity
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
	        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);

	        java.util.Date utilDate = dateFormat.parse(appointmentDTO.getAppointmentDate());
	        java.util.Date utilTime = timeFormat.parse(appointmentDTO.getAppointmentHour());

	        Date sqlDate = new Date(utilDate.getTime());
	        Time sqlTime = new Time(utilTime.getTime());

	        // Retrieve and set User entity
	        Optional<User> userOpt = userRep.findByName(appointmentDTO.getUsername());
	        if (!userOpt.isPresent()) {
	            throw new UserNotFoundException("User not found with name: " + appointmentDTO.getUsername());
	        }

	        // Retrieve and set SportGround entity
	        Optional<SportGround> sportGroundOpt = grRep.findByName(appointmentDTO.getSportGround());
	        if (!sportGroundOpt.isPresent()) {
	            throw new SportGroundNotFoundException("Sport ground not found with name: " + appointmentDTO.getSportGround());
	        }

	        createdAppointment.setId(appointmentDTO.getId());
	        createdAppointment.setAppointmentDate(sqlDate);
	        createdAppointment.setAppointmentHour(sqlTime);
	        createdAppointment.setNop(appointmentDTO.getNop());
	        createdAppointment.setTrainerName(appointmentDTO.getTrainerName());
	        createdAppointment.setUser(userOpt.get());
	        createdAppointment.setSportGround(sportGroundOpt.get());
	    } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	public Appointment getAppointment() {
		return this.createdAppointment;
	}

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
			if(appointment.getTrainerName().equals(""))
			{
				throw new IllegalStateException("Appointment with these dates already exists");
			}
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



