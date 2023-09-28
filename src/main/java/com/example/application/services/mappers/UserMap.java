package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.UserDTO;
import com.example.application.entities.Appointment;
import com.example.application.entities.User;

@Mapper
public interface UserMap {

	@Mapping(source = "user.idUser", target = "idUser")
	@Mapping(source = "user.appointmentsList", target = "appointmentsList", qualifiedBy = AppointmentListToAppointmentListDTO.class)
	public UserDTO toDTO(User user);
	
	@AppointmentListToAppointmentListDTO
	public default List<AppointmentDTO> mapAppointments(List<Appointment> list)
	{
		if(list == null)
		{
			return null;
		}
		
		List<AppointmentDTO> dtos = new ArrayList<>();
		for(Appointment appointment : list)
		{
			AppointmentDTO appointmentDTO = new AppointmentDTO();
			appointmentDTO.setId(appointment.getId());
			appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
			appointmentDTO.setAppointmentHour(appointment.getAppointmentHour());
			dtos.add(appointmentDTO);
		}
		return dtos;
	}
}
