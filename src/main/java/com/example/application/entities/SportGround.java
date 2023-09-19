package com.example.application.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class SportGround {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long capacity;
    private String name;

    @ManyToOne
    @JoinColumn(name = "sports_base_id")
    private SportsBase sportsBase;
    
	@OneToMany(mappedBy = "sportGround",cascade = CascadeType.ALL)
	private List<Appointment> appointmentsList;
	
	public SportGround() {
	}

	public SportGround(Long id) {
		this.id = id;
	}
	
    
}
