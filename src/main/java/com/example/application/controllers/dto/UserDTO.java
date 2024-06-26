package com.example.application.controllers.dto;

import java.util.List;

import com.example.application.entities.Appointment;
import com.example.application.entities.Person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class UserDTO extends Person {
	
    private Long idUser;
    private List<AppointmentDTO> appointmentsList;
    
}
