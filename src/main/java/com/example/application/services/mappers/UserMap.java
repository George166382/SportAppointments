package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.UserDTO;
import com.example.application.entities.Appointment;
import com.example.application.entities.User;

@Component
public class UserMap {

	public UserDTO toDTO(User user) {
		if (user == null) {
			return null;
		}

		UserDTO userDTO = new UserDTO();
		userDTO.setIdUser(user.getIdUser());
		userDTO.setAppointmentsList(mapAppointments(user.getAppointmentsList()));
		return userDTO;
	}

	public List<AppointmentDTO> mapAppointments(List<Appointment> list) {
		if (list == null) {
			return null;
		}

		List<AppointmentDTO> dtos = new ArrayList<>();
		for (Appointment appointment : list) {
			AppointmentDTO appointmentDTO = new AppointmentDTO();
			appointmentDTO.setId(appointment.getId());
			appointmentDTO.setNop(appointment.getNop());
			appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
			appointmentDTO.setAppointmentHour(appointment.getAppointmentHour());
			dtos.add(appointmentDTO);
		}
		return dtos;
	}
}