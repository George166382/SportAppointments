package com.example.application.controllers.dto;

import java.sql.Date;
import java.sql.Time;

import com.example.application.entities.SportGround;
import com.example.application.entities.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class AppointmentDTO2 {
	
    private Long id;

   
    private String username;

   
    private String sportGround;
    
    private int nop;

    private String trainerName;
    private String appointmentDate;

   
    private String appointmentHour;
    
    
}
