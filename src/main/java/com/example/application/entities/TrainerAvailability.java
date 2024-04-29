package com.example.application.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class TrainerAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date availableDate;
    private String availableHour;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public TrainerAvailability() {
    }
}
