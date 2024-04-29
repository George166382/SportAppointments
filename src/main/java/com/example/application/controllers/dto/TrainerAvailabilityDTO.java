package com.example.application.controllers.dto;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

import com.example.application.entities.Trainer;

@Data
public class TrainerAvailabilityDTO {
    private Long id;
    private Date availableDate;
    private String availableHour;
    private TrainerDTO trainer;
    // You can add more fields if needed

}
