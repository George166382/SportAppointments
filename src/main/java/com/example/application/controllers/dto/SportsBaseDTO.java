package com.example.application.controllers.dto;

import java.util.List;

import com.example.application.entities.Admin;
import com.example.application.entities.SportGround;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class SportsBaseDTO {
	
    private Long id;
    private String name;
    private String address;
    private AdminDTO admin;
    private List<SportGroundDTO> groundsList;
    
}
