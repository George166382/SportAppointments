package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.UserDTO;
import com.example.application.entities.Appointment;
import com.example.application.entities.User;

@Component
public class UserMapper {
	
	@Autowired
	private AppointmentMapper appointmentMapper;

	public UserDTO toDTO(User user)
	{
		UserDTO userDTO = new UserDTO();
		userDTO.setIdUser(user.getIdUser());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		List<AppointmentDTO> appointmentsListDTO = new ArrayList<>();
		for(Appointment appointment : user.getAppointmentsList())
		{
			appointmentsListDTO.add(appointmentMapper.toDTO(appointment));
		}
		userDTO.setAppointmentsList(appointmentsListDTO);
		return userDTO;
	}
}
