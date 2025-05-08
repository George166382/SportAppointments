package com.example.application.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;




@Entity
@Table(name = "basic_user")
@Getter
@Setter
public class User extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long idUser;
    
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL
    		  )
    private List<Appointment> appointmentsList;
    
    public User() {
	}
	
	public User(Long idUser) {
		this.idUser = idUser;
	}


    public User(String email, String s) {
        super();
    }
}

