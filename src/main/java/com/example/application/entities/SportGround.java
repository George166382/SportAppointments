package com.example.application.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
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

	public SportsBase getSportsBase() {
		return sportsBase;
	}

	public void setSportsBase(SportsBase sportsBase) {
		this.sportsBase = sportsBase;
	}

	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
