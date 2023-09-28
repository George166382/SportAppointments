package com.example.application.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.controllers.dto.UserDTO;
import com.example.application.entities.Appointment;
import com.example.application.entities.SportGround;
import com.example.application.entities.User;

@Mapper
public interface AppointmentMap {

	@Mapping(source = "appointment.id", target = "id")
	@Mapping(source = "appointment.user", target = "user", qualifiedBy = UserToUserDTO.class)
	@Mapping(source = "appointment.sportGround", target = "sportGround", qualifiedBy = SportGroundToSportGroundDTO.class)
	public AppointmentDTO toDTO(Appointment appointment);
	
	@UserToUserDTO
	public default UserDTO mapUser(User user)
	{
		UserDTO userDTO = new UserDTO();
		userDTO.setIdUser(user.getIdUser());
		userDTO.setEmail(user.getEmail());
		userDTO.setName(userDTO.getName());
		return userDTO;
	}
	@SportGroundToSportGroundDTO
	public default SportGroundDTO mapSportGround(SportGround sportGround)
	{
		SportGroundDTO sportGroundDTO = new SportGroundDTO();
		sportGroundDTO.setId(sportGround.getId());
		sportGroundDTO.setName(sportGround.getName());
		sportGroundDTO.setCapacity(sportGround.getCapacity());
		return sportGroundDTO;
	}
}
