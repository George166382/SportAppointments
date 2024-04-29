package com.example.application.controllers.dto;

import java.util.List;

import com.example.application.entities.SportGround;
import com.example.application.entities.TrainerAvailability;

import lombok.Data;

@Data
public class TrainerDTO {
    private Long id;
    private String name;
    private SportGroundDTO sportGround;
    private List<TrainerAvailabilityDTO> availabilityList;
    // You can add more fields if needed

}
