package com.example.application.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;




@Entity
public class User extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
   
    private Long idUser;
    
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL
    		  )
    private List<Appointment> appointmentsList;
    
    

	public List<Appointment> getAppointmentsList() {
		return appointmentsList;
	}

	public void setAppointmentsList(List<Appointment> appointmentsList) {
		this.appointmentsList = appointmentsList;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

   
    
    
}

