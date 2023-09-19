package com.example.application.controllers.dto;

import java.util.List;

import com.example.application.entities.Appointment;
import com.example.application.entities.SportsBase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
public class SportGroundDTO {
	
    private Long id;
    private Long capacity;
    private String name;
    private SportsBaseDTO sportsBase;
    private List<AppointmentDTO> appointmentsList;
    
    
}
