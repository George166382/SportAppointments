package com.example.application.services.mappers;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.controllers.dto.TrainerDTO;
import com.example.application.entities.Appointment;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;
import com.example.application.entities.Trainer;

@Mapper
public interface SportGroundMap {

	@Mapping(source = "sportGround.id", target = "id")
    @Mapping(source = "sportGround.sportsBase", target = "sportsBase", qualifiedBy = SportsBaseToSportsBaseDTO.class)
    @Mapping(source = "sportGround.appointmentsList", target = "appointmentsList", qualifiedBy = AppointmentListToAppointmentListDTO.class)
    @Mapping(source = "sportGround.trainersList", target = "trainersList", qualifiedBy = TrainerListToTrainerListDTO.class)
    public SportGroundDTO toDTO(SportGround sportGround);
	
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
			appointmentDTO.setNop(appointment.getNop());
			appointmentDTO.setTrainerName(appointment.getTrainerName());
			appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
			appointmentDTO.setAppointmentHour(appointment.getAppointmentHour());
			dtos.add(appointmentDTO);
		}
		return dtos;
	}
	
	@SportsBaseToSportsBaseDTO
	public default SportsBaseDTO mapSportsBase(SportsBase sportsBase)
	{
		SportsBaseDTO sportsBaseDTO = new SportsBaseDTO();
		sportsBaseDTO.setAddress(sportsBase.getAddress());
		sportsBaseDTO.setId(sportsBase.getId());
		sportsBaseDTO.setName(sportsBase.getName());
		return sportsBaseDTO;
	}
	
	
	@TrainerListToTrainerListDTO
	public default List<TrainerDTO> mapTrainers(List<Trainer> list)
	{
		if(list == null)
		{
			return null;
		}
		
		List<TrainerDTO> dtos = new ArrayList<>();
		for(Trainer trainer : list)
		{
			TrainerDTO trainerDTO = new TrainerDTO();
			trainerDTO.setId(trainer.getId());
			trainerDTO.setName(trainer.getName());
			//trainerDTO.setAvailabilityList(trainer.getAvailabilityList());
			dtos.add(trainerDTO);
		}
		return dtos;
	}
}
