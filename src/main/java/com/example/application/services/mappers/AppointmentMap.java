package com.example.application.services.mappers;

import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.UserDTO;
import com.example.application.entities.Appointment;
import com.example.application.entities.SportGround;
import com.example.application.entities.User;

@Component
public class AppointmentMap {

	public AppointmentDTO toDTO(Appointment appointment) {
		if (appointment == null) {
			return null;
		}

		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setId(appointment.getId());
		appointmentDTO.setUser(mapUser(appointment.getUser()));
		appointmentDTO.setSportGround(mapSportGround(appointment.getSportGround()));
		return appointmentDTO;
	}

	public UserDTO mapUser(User user) {
		if (user == null) {
			return null;
		}

		UserDTO userDTO = new UserDTO();
		userDTO.setIdUser(user.getIdUser());
		userDTO.setEmail(user.getEmail());
		userDTO.setName(user.getName());
		return userDTO;
	}

	public SportGroundDTO mapSportGround(SportGround sportGround) {
		if (sportGround == null) {
			return null;
		}

		SportGroundDTO sportGroundDTO = new SportGroundDTO();
		sportGroundDTO.setId(sportGround.getId());
		sportGroundDTO.setName(sportGround.getName());
		sportGroundDTO.setCapacity(sportGround.getCapacity());
		return sportGroundDTO;
	}
}