package com.example.application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.sql.Date; // Use java.sql.Date for DATE type
import java.sql.Time; // Use java.sql.Time for TIME type

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "sport_ground_id")
    private SportGround sportGround;

    @Column(name = "appointment_date")
    @Temporal(TemporalType.DATE) 
    private Date appointmentDate;

    @Column(name = "appointment_hour")
    private Time appointmentHour;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SportGround getSportGround() {
		return sportGround;
	}

	public void setSportGround(SportGround sportGround) {
		this.sportGround = sportGround;
	}
    
    
    
}
