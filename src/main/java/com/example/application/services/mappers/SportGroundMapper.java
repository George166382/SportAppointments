package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.entities.Appointment;
import com.example.application.entities.SportGround;

@Component
public class SportGroundMapper {
	@Autowired
	private AppointmentMapper appointmentMapper;

	public SportGroundDTO toDTO(SportGround sportGround)
	{
		SportGroundDTO groundDTO = new SportGroundDTO();
		groundDTO.setId(sportGround.getId());
		List<AppointmentDTO> appointmentsListDTO = new ArrayList<>();
		for(Appointment appointment : sportGround.getAppointmentsList())
		{
			appointmentsListDTO.add(appointmentMapper.toDTO(appointment));
		}
		groundDTO.setAppointmentsList(appointmentsListDTO);
		return groundDTO;
	}
}
