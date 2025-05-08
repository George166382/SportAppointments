package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.Appointment;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;
import com.example.application.entities.Trainer;

@Component
public class SportGroundMap {

	public static SportGroundDTO toDTO(SportGround sportGround) {
		if (sportGround == null) {
			return null;
		}

		SportGroundDTO sportGroundDTO = new SportGroundDTO();
		sportGroundDTO.setId(sportGround.getId());
		sportGroundDTO.setSportsBase(mapSportsBase(sportGround.getSportsBase()));
		sportGroundDTO.setAppointmentsList(mapAppointments(sportGround.getAppointmentsList()));
		sportGroundDTO.setTrainersList(mapTrainers(sportGround.getTrainersList()));
		return sportGroundDTO;
	}

	public static List<AppointmentDTO> mapAppointments(List<Appointment> list) {
		if (list == null) {
			return null;
		}

		List<AppointmentDTO> dtos = new ArrayList<>();
		for (Appointment appointment : list) {
			AppointmentDTO appointmentDTO = new AppointmentDTO();
			appointmentDTO.setId(appointment.getId());
			appointmentDTO.setNop(appointment.getNop());
			appointmentDTO.setTrainerName(appointment.getTrainerName());
			appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
			appointmentDTO.setAppointmentHour(appointment.getAppointmentHour());
			dtos.add(appointmentDTO);
		}
		return dtos;
	}

	public static SportsBaseDTO mapSportsBase(SportsBase sportsBase) {
		if (sportsBase == null) {
			return null;
		}

		SportsBaseDTO sportsBaseDTO = new SportsBaseDTO();
		sportsBaseDTO.setAddress(sportsBase.getAddress());
		sportsBaseDTO.setId(sportsBase.getId());
		sportsBaseDTO.setName(sportsBase.getName());
		return sportsBaseDTO;
	}

	public static List<TrainerDTO> mapTrainers(List<Trainer> list) {
		if (list == null) {
			return null;
		}

		List<TrainerDTO> dtos = new ArrayList<>();
		for (Trainer trainer : list) {
			TrainerDTO trainerDTO = new TrainerDTO();
			trainerDTO.setId(trainer.getId());
			trainerDTO.setName(trainer.getName());
			dtos.add(trainerDTO);
		}
		return dtos;
	}
}