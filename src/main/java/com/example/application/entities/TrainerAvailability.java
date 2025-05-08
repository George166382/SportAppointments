package com.example.application.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TrainerAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="available_date")
    private Date availableDate;
    @Column(name="available_hour")
    private String availableHour;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public TrainerAvailability() {
    }

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
